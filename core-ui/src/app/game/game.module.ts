import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameComponent } from './layout/layout-with-score/game/game.component';
import {GameListComponent} from './layout/game-list/game-list.component';
import { LayoutComponent } from './layout/layout.component';
import {RouterModule} from '@angular/router';
import { LayoutWithScoreComponent } from './layout/layout-with-score/layout-with-score.component';
import { RoundComponent } from './layout/layout-with-score/round/round.component';
import { SuperRoundComponent } from './layout/layout-with-score/super-round/super-round.component';
import { QuestionComponent } from './layout/layout-with-score/question/question.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    GameComponent,
    GameListComponent,
    LayoutComponent,
    LayoutWithScoreComponent,
    RoundComponent,
    SuperRoundComponent,
    QuestionComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule
  ]
})
export class GameModule { }
