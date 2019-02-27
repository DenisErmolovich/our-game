import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home/home.component';
import {GameListComponent} from './game/layout/game-list/game-list.component';
import {GameComponent} from './game/layout/layout-with-score/game/game.component';
import {RoundComponent} from './game/layout/layout-with-score/round/round.component';
import {SuperRoundComponent} from './game/layout/layout-with-score/super-round/super-round.component';
import {QuestionComponent} from './game/layout/layout-with-score/question/question.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
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
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
