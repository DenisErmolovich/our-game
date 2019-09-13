import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup} from '@angular/forms';
import {ValidationUtil} from '../../_validation/validation-util';
import {CustomValidators} from '../../_validation/custom-validators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private _form: FormGroup;

  constructor(
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this._form = this.initForm();
  }

  public get form(): FormGroup {
    return this._form;
  }

  private initForm(): FormGroup {
    return this.fb.group({
      login: ['', [CustomValidators.required(), CustomValidators.email()]],
      password: ['', [CustomValidators.required(), CustomValidators.minLength(6)]]
    });
  }

  public getValidationMessage(controlName: string): string {
    const control: AbstractControl = this._form.controls[controlName];
    return ValidationUtil.getCustomErrorsFromControl(control);
  }

  public onSubmit(): void {
    this._form.markAllAsTouched();
    if (this._form.valid) {
      console.log('Login action...');
    }
  }

}
