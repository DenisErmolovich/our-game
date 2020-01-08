import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameListComponent } from './game-list/game-list.component';
import {ConstructorRoutingModule} from './constructor-routing.module';



@NgModule({
  declarations: [GameListComponent],
  imports: [
    CommonModule,
    ConstructorRoutingModule
  ]
})
export class ConstructorModule { }
