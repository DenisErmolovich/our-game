import { Injectable } from '@angular/core';
import {BaseComponentCommunication} from './base-component-communication';

@Injectable({
  providedIn: 'root'
})
export class ScoreUpdateService extends BaseComponentCommunication<any> {

  constructor() {
    super();
  }
}
