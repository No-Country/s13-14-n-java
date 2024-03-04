import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'extractDate',
})
export class ExtractDatePipe implements PipeTransform {
  transform(value: string | undefined): string {
    const index = value?.indexOf('T');
    return index !== undefined && index !== -1
      ? value!.substring(0, index).trim()
      : value ?? '';
  }
}
