import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LayoutComponent} from './layout.component';
import {RouterModule} from '@angular/router';
import {ConstructorModule} from './constructor/constructor.module';



@NgModule({
  declarations: [
    LayoutComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ConstructorModule
  ],
  exports: [
    LayoutComponent
  ]
})
export class LayoutModule { }
