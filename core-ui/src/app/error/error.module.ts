import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BallonErrorComponent } from './ballon-error/ballon-error.component';

@NgModule({
  declarations: [BallonErrorComponent],
  imports: [
    CommonModule
  ],
  exports: [BallonErrorComponent]
})
export class ErrorModule { }
