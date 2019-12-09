import { Injectable } from '@angular/core';
import {BaseComponentCommunication} from '../component-communication/base-component-communication';
import {ErrorBase} from '../../_models/error-base';

@Injectable({
  providedIn: 'root'
})
export class BalloonErrorService extends BaseComponentCommunication<ErrorBase> {

  constructor() {
    super();
  }

  public sendError(errorText: string): void {
    const error: ErrorBase = {
      description: errorText
    };
    this.setData(error);
    this.sendData();
  }
}
