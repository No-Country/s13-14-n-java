import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'firstChar',
})
export class FirstCharPipe implements PipeTransform {
  transform(value: string | undefined): string {
    if (value === undefined || value === null || value === '') {
      return '';
    }
    return value.charAt(0);
  }
}
