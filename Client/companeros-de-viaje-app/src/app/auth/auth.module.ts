import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { ResetPassPageComponent } from './reset-pass-page/reset-pass-page.component';
import { LayoutComponent } from './layout/layout.component';
import { PrimeNgModule } from '../shared/prime-ng/prime-ng.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProfileRegPageComponent } from './profile-reg-page/profile-reg-page.component';
import { HeaderComponent } from './components/header/header.component';

@NgModule({
  declarations: [
    LoginPageComponent,
    RegisterPageComponent,
    ResetPassPageComponent,
    LayoutComponent,
    ProfileRegPageComponent,
    HeaderComponent,
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    PrimeNgModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
})
export class AuthModule {}
