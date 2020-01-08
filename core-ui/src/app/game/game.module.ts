import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GameComponent} from './layout-with-score/game/game.component';
import {GameListComponent} from './game-list/game-list.component';
import {RouterModule} from '@angular/router';
import {LayoutWithScoreComponent} from './layout-with-score/layout-with-score.component';
import {RoundComponent} from './layout-with-score/round/round.component';
import {SuperRoundComponent} from './layout-with-score/super-round/super-round.component';
import {QuestionComponent} from './layout-with-score/question/question.component';
import {ReactiveFormsModule} from '@angular/forms';
import {GameRoutingModule} from './game-routing.module';
import {LayoutModule} from '../layout/layout.module';

@NgModule({
  declarations: [
    GameComponent,
    GameListComponent,
    LayoutWithScoreComponent,
    RoundComponent,
    SuperRoundComponent,
    QuestionComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    GameRoutingModule,
    ReactiveFormsModule,
    LayoutModule
  ]
})
export class GameModule {
}
