import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateButtonComponent } from './create-button/create-button.component';



@NgModule({
  declarations: [
    CreateButtonComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CreateButtonComponent
  ]
})
export class SharedModule { }
