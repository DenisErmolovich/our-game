import {AbstractControl} from '@angular/forms';

export class ValidationUtil {
  private constructor() {}

  public static getCustomErrorsFromControl(control: AbstractControl): string | null {
    let message = '';
    if (control.valid || (control.untouched && control.pristine)) {
      return null;
    }
    for (const key of Object.keys(control.errors)) {
      const error = control.errors[key].customMessage ? control.errors[key].customMessage : 'Use CustomValidators!';
      message += error + ' ';
    }
    return message;
  }
}
