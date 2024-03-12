import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Err404PageComponent } from './shared/err404-page/err404-page.component';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'cdv',
    loadChildren: () => import('./cdv/cdv.module').then((m) => m.CdvModule),
  },
  {
    path: 'book',
    loadChildren: () =>
      import('./booking/booking.module').then((m) => m.BookingModule),
  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./dashboard/dashboard.module').then((m) => m.DashboardModule),
  },
  {
    path: '404',
    component: Err404PageComponent,
  },
  {
    path: '',
    redirectTo: 'cdv',
    pathMatch: 'full',
  },
  {
    path: '**',
    redirectTo: '404',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
