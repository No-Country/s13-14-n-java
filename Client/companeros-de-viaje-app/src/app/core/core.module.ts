import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastComponent } from './components/toast/toast.component';
import { PrimeNgModule } from '../shared/prime-ng/prime-ng.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [ToastComponent],
  imports: [CommonModule, PrimeNgModule, BrowserAnimationsModule],
  exports: [ToastComponent],
})
export class CoreModule {}
