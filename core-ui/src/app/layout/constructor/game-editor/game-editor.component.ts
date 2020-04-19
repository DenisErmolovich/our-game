import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup} from '@angular/forms';
import {CustomValidators} from '../../../_validation/custom-validators';
import {ValidationUtil} from '../../../_validation/validation-util';
import {Location} from '@angular/common';
import {Game} from '../../../_models/game';
import {GameService} from '../../../_services/data/http/game.service';
import {BalloonErrorService} from '../../../_services/errors/balloon-error.service';
import {ActivatedRoute} from '@angular/router';
import {flatMap} from 'rxjs/operators';
import {of} from "rxjs";

@Component({
  selector: 'app-game-redactor',
  templateUrl: './game-editor.component.html',
  styleUrls: ['./game-editor.component.scss']
})
export class GameEditorComponent implements OnInit {
  private _form: FormGroup;
  private _id: string;

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private gameService: GameService,
    private errorService: BalloonErrorService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.queryParamMap.pipe(
      flatMap(params => of(params.get('id')))
    ).subscribe(id => {
      this.onInit(id);
    });
  }

  get form(): FormGroup {
    return this._form;
  }

  get id(): string {
    return this._id;
  }

  onSubmit(): void {
    this._form.markAllAsTouched();
    if (this._form.invalid) {
      return;
    }
    const name = this._form.controls.name.value;
    if (this.id) {
      const id = this._id;
      this.updateGame({id, name});
    } else {
      this.saveGame({name});
    }
  }

  private onInit(id: string): void {
    this._id = id;
    this._form = this.initForm();
    if (id) {
      this.gameService.findById(id).subscribe(
        game => this.fillForm(this._form, game),
        error => this.errorService.sendError(JSON.stringify(error))
      );
    }
  }

  getValidationMessage(controlName: string): string {
    const control: AbstractControl = this._form.controls[controlName];
    return ValidationUtil.getCustomErrorsFromControl(control);
  }

  private initForm(): FormGroup {
    return this.fb.group({
      name: ['', [CustomValidators.required()]]
    });
  }

  private fillForm(form: FormGroup, object: object): void {
    Object.keys(object).forEach(key => {
      const control = form.controls[key];
      if (control) {
        control.setValue(object[key]);
      }
    });
  }

  private saveGame(game: Game): void {
    this.gameService.save(game).subscribe(
      response => this.location.back(),
      error => this.errorService.sendError(JSON.stringify(error))
    );
  }

  private updateGame(game: Game): void {
    this.gameService.update(game).subscribe(
      response => this.location.back(),
      error => this.errorService.sendError(JSON.stringify(error))
    );
  }
}
