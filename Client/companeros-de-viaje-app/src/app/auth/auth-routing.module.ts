import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { ResetPassPageComponent } from './reset-pass-page/reset-pass-page.component';
import { ProfileRegPageComponent } from './profile-reg-page/profile-reg-page.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'login',
        component: LoginPageComponent,
      },
      {
        path: 'new-account',
        component: RegisterPageComponent,
      },
      {
        path: 'profile-register',
        component: ProfileRegPageComponent,
      },
      {
        path: 'reset-pass',
        component: ResetPassPageComponent,
      },
      {
        path: '**',
        redirectTo: 'login',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
