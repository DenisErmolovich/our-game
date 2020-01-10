import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BalloonErrorComponent } from './balloon-error/balloon-error.component';

@NgModule({
  declarations: [BalloonErrorComponent],
  imports: [
    CommonModule
  ],
  exports: [BalloonErrorComponent]
})
export class ErrorModule { }
