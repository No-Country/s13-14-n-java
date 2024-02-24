import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderPageComponent } from './header-page/header-page.component';
import { Err404PageComponent } from './err404-page/err404-page.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule } from '@angular/forms';
import { PrimeNgModule } from './prime-ng/prime-ng.module';
import { HomeCardComponent } from './home-card/home-card.component';

@NgModule({
  declarations: [
    HeaderPageComponent,
    Err404PageComponent,
    NavBarComponent,
    FooterComponent,
    HomeCardComponent,
  ],
  imports: [CommonModule, FormsModule, PrimeNgModule],
  exports: [
    HeaderPageComponent,
    Err404PageComponent,
    NavBarComponent,
    FooterComponent,
    HomeCardComponent,
  ],
})
export class SharedModule {}
