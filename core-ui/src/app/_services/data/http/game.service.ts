import { Injectable } from '@angular/core';
import {BaseCrudService} from './base-crud.service';
import {Game} from '../../../_models/game';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameService extends BaseCrudService<Game> {

  constructor(
    protected http: HttpClient
  ) {
    super(http, '/game/games');
  }
}
