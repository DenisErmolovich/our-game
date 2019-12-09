import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup} from '@angular/forms';
import {ValidationUtil} from '../../_validation/validation-util';
import {CustomValidators} from '../../_validation/custom-validators';
import {AuthService} from "../../_services/auth/auth.service";
import {AuthRequest} from "../../_models/auth-request";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private _form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private auth: AuthService
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
    if (this._form.invalid) {
      return;
    }
    const authRequest: AuthRequest = {
      login: this._form.controls['login'].value,
      password: this._form.controls['password'].value
    };
    this.auth.login(authRequest);
  }

}
