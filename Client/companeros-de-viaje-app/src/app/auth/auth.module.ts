import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { ResetPassPageComponent } from './reset-pass-page/reset-pass-page.component';
import { LayoutComponent } from './layout/layout.component';


@NgModule({
  declarations: [
    LoginPageComponent,
    RegisterPageComponent,
    ResetPassPageComponent,
    LayoutComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule
  ]
})
export class AuthModule { }
