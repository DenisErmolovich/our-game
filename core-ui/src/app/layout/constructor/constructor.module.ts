import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameListComponent } from './game-list/game-list.component';
import {ConstructorRoutingModule} from './constructor-routing.module';
import { GameEditorComponent } from './game-editor/game-editor.component';
import {SharedModule} from '../../_shared/shared.module';
import {ReactiveFormsModule} from '@angular/forms';
import { GameRowComponent } from './game-list/game-row/game-row.component';



@NgModule({
  declarations: [
    GameListComponent,
    GameEditorComponent,
    GameRowComponent
  ],
  imports: [
    CommonModule,
    ConstructorRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class ConstructorModule { }
