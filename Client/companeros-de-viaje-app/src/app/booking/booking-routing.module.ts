import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { HomeComponent } from './home/home.component';
import { DetailComponent } from './detail/detail.component';
import { ListComponent } from './list/list.component';
import { NewGroupComponent } from './new-group/new-group.component';
import { EditGroupComponent } from './edit-group/edit-group.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'detail/:id',
        component: DetailComponent,
      },
      {
        path: 'list',
        component: ListComponent,
      },
      {
        path: 'new-group',
        component: NewGroupComponent,
      },
      {
        path: 'edit/:id',
        component: EditGroupComponent,
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
export class BookingRoutingModule {}
