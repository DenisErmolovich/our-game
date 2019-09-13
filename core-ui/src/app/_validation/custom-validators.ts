import {AbstractControl, ValidatorFn} from '@angular/forms';

export class CustomValidators {
  private constructor() {}

  public static required(): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      return control.value ? null : {required: {customMessage: 'Обязательное поле!'}};
    };
  }

  public static email(): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      const value = control.value as string;
      if (!value) {
        return null;
      }
      const reqexp = new RegExp(`^( )*[\\w\\"!#$%&’*+/=?\`{|}~^-]+(?:\\.[\\'\\w!#$%&’*+/=?\`{|}~^-]+)*@{1}`
        + `((?:[a-zA-Z0-9]+(\\.(?!web$)|-))+[a-zA-Z]{2,6}|\\[?([0-9]{1,3}\\.){3}[0-9]{1,3}\\]?)( )*$`);
      return value.match(reqexp) ? null : {email: {customMessage: 'Не верный email!'}};
    };
  }

  public static minLength(minLength: number): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      const value = control.value as string;
      const errorText = `Количество символов в поле должно быть не меньше ${minLength}!`;
      return value.length < minLength ? {email: {customMessage: errorText}} : null;
    };
  }

}
