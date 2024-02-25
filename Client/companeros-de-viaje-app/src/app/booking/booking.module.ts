import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookingRoutingModule } from './booking-routing.module';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';
import { ListComponent } from './list/list.component';
import { DetailComponent } from './detail/detail.component';
import { NewGroupComponent } from './new-group/new-group.component';
import { PrimeNgModule } from '../shared/prime-ng/prime-ng.module';

@NgModule({
  declarations: [
    HomeComponent,
    LayoutComponent,
    ListComponent,
    DetailComponent,
    NewGroupComponent,
  ],
  imports: [CommonModule, BookingRoutingModule, PrimeNgModule],
})
export class BookingModule {}
