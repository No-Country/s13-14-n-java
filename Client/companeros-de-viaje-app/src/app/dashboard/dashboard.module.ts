import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { ListComponent } from './list/list.component';
import { DetailComponent } from './detail/detail.component';
import { PrimeNgModule } from '../shared/prime-ng/prime-ng.module';
import { SharedModule } from '../shared/shared.module';
import { PipesModule } from '../core/pipes/pipes.module';

@NgModule({
  declarations: [LayoutComponent, ListComponent, DetailComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    PrimeNgModule,
    SharedModule,
    PipesModule,
  ],
})
export class DashboardModule {}
