import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CdvRoutingModule } from './cdv-routing.module';
import { HomePageComponent } from './home-page/home-page.component';
import { LayoutComponent } from './layout/layout.component';
import { BookingPageComponent } from './booking-page/booking-page.component';
import { SharedModule } from '../shared/shared.module';
import { NavBarComponent } from '../components/nav-bar/nav-bar.component';
import { FooterComponent } from '../components/footer/footer.component';

@NgModule({
  declarations: [HomePageComponent, LayoutComponent, BookingPageComponent, FooterComponent, NavBarComponent],
  imports: [CommonModule, CdvRoutingModule, SharedModule],
})
export class CdvModule {}
