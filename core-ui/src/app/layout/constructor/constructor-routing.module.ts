import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from '../layout.component';
import {GameListComponent} from './game-list/game-list.component';

const routes: Routes = [
  {
    path: 'constructor',
    component: LayoutComponent,
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'games'
      },
      {
        path: 'games',
        component: GameListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConstructorRoutingModule { }
