import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from '../layout.component';
import {GameListComponent} from './game-list/game-list.component';
import {GameEditorComponent} from './game-editor/game-editor.component';

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
        component: GameListComponent,
      },
      {
        path: 'games/editor',
        component: GameEditorComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConstructorRoutingModule { }
