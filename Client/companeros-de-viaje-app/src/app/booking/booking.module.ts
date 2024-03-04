import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookingRoutingModule } from './booking-routing.module';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';
import { ListComponent } from './list/list.component';
import { DetailComponent } from './detail/detail.component';
import { NewGroupComponent } from './new-group/new-group.component';
import { PrimeNgModule } from '../shared/prime-ng/prime-ng.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { PipesModule } from '../core/pipes/pipes.module';
import { EditGroupComponent } from './edit-group/edit-group.component';

@NgModule({
  declarations: [
    HomeComponent,
    LayoutComponent,
    ListComponent,
    DetailComponent,
    NewGroupComponent,
    EditGroupComponent,
  ],
  imports: [
    CommonModule,
    BookingRoutingModule,
    PrimeNgModule,
    ReactiveFormsModule,
    SharedModule,
    PipesModule,
    FormsModule,
  ],
})
export class BookingModule {}
