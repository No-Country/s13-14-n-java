import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExtractDatePipe } from './extract-date.pipe';
import { FirstCharPipe } from './first-char.pipe';

@NgModule({
  declarations: [ExtractDatePipe, FirstCharPipe],
  imports: [CommonModule],
  exports: [ExtractDatePipe, FirstCharPipe],
})
export class PipesModule {}
