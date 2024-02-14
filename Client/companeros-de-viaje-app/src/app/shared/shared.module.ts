import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderPageComponent } from './header-page/header-page.component';
import { Err404PageComponent } from './err404-page/err404-page.component';



@NgModule({
  declarations: [
    HeaderPageComponent,
    Err404PageComponent
  ],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
