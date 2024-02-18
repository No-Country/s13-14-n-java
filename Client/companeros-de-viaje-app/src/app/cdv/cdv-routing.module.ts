import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { HomePageComponent } from './home-page/home-page.component';
import { BookingPageComponent } from './booking-page/booking-page.component';
import { NavBarComponent } from '../components/nav-bar/nav-bar.component';
import { FooterComponent } from '../components/footer/footer.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'home',
        component: HomePageComponent,
      },
      {
        path: 'booking',
        component: BookingPageComponent,
      },
      {
        path: '**',
        redirectTo: 'home',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CdvRoutingModule {}
