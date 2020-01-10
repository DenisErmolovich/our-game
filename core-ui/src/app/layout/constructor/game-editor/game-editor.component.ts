import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup} from '@angular/forms';
import {CustomValidators} from '../../../_validation/custom-validators';
import {ValidationUtil} from '../../../_validation/validation-util';
import {Location} from '@angular/common';
import {Game} from "../../../_models/game";
import {GameService} from "../../../_services/data/http/game.service";
import {BalloonErrorService} from "../../../_services/errors/balloon-error.service";

@Component({
  selector: 'app-game-redactor',
  templateUrl: './game-editor.component.html',
  styleUrls: ['./game-editor.component.scss']
})
export class GameEditorComponent implements OnInit {
  private _form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private gameService: GameService,
    private errorService: BalloonErrorService
  ) { }

  ngOnInit() {
    this._form = this.initForm();
  }

  get form(): FormGroup {
    return this._form;
  }

  onSubmit(): void {
    this._form.markAllAsTouched();
    if (this._form.invalid) {
      return;
    }
    const name = this._form.controls.name.value;
    this.saveGame({name});
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

  private saveGame(game: Game): void {
    this.gameService.save(game).subscribe(
      response => this.location.back(),
      error => this.errorService.sendError(JSON.stringify(error))
    );
  }
}
