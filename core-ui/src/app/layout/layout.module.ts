import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LayoutComponent} from './layout.component';
import {RouterModule} from '@angular/router';
import {ConstructorModule} from './constructor/constructor.module';
import {ErrorModule} from '../error/error.module';



@NgModule({
  declarations: [
    LayoutComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ConstructorModule,
    ErrorModule
  ],
  exports: [
    LayoutComponent
  ]
})
export class LayoutModule { }
