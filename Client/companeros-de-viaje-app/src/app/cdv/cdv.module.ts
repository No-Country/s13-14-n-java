import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CdvRoutingModule } from './cdv-routing.module';
import { HomePageComponent } from './home-page/home-page.component';
import { LayoutComponent } from './layout/layout.component';
import { SharedModule } from '../shared/shared.module';
import { PrimeNgModule } from '../shared/prime-ng/prime-ng.module';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';
import { PrivacyPolicyComponent } from './privacy-policy/privacy-policy.component';
import { TermsComponent } from './terms/terms.component';
import { PipesModule } from '../core/pipes/pipes.module';

@NgModule({
  declarations: [
    HomePageComponent,
    LayoutComponent,
    AboutUsComponent,
    ContactComponent,
    PrivacyPolicyComponent,
    TermsComponent,
  ],
  imports: [
    CommonModule,
    CdvRoutingModule,
    SharedModule,
    PrimeNgModule,
    PipesModule,
  ],
})
export class CdvModule {}
