import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GameListComponent} from './layout/game-list/game-list.component';
import {GameComponent} from './layout/layout-with-score/game/game.component';
import {SuperRoundComponent} from './layout/layout-with-score/super-round/super-round.component';
import {RoundComponent} from './layout/layout-with-score/round/round.component';
import {QuestionComponent} from './layout/layout-with-score/question/question.component';

const routes: Routes = [
  {
    path: 'game',
    component: GameListComponent
  },
  {
    path: 'game/:gameId',
    component: GameComponent
  },
  {
    path: 'game/:gameId/round/super',
    component: SuperRoundComponent
  },
  {
    path: 'game/:gameId/round/:roundId',
    component: RoundComponent
  },
  {
    path: 'game/:gameId/round/:roundId/question/:questionId',
    component: QuestionComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class GameRoutingModule { }
