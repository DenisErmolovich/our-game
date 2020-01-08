import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GameListComponent} from './game-list/game-list.component';
import {GameComponent} from './layout-with-score/game/game.component';
import {SuperRoundComponent} from './layout-with-score/super-round/super-round.component';
import {RoundComponent} from './layout-with-score/round/round.component';
import {QuestionComponent} from './layout-with-score/question/question.component';
import {PlayerRoleGuard} from '../_guards/player-role.guard';

const routes: Routes = [
  {
    path: '',
    component: GameListComponent,
    pathMatch: 'full',
    canActivate: [PlayerRoleGuard]
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
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GameRoutingModule { }
