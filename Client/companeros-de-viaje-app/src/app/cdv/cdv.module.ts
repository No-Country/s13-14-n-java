import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CdvRoutingModule } from './cdv-routing.module';
import { HomePageComponent } from './home-page/home-page.component';
import { LayoutComponent } from './layout/layout.component';
import { BookingPageComponent } from './booking-page/booking-page.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [HomePageComponent, LayoutComponent, BookingPageComponent],
  imports: [CommonModule, CdvRoutingModule, SharedModule],
})
export class CdvModule {}
