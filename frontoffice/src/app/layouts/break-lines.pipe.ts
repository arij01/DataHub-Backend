import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'breakLines' })
export class BreakLinesPipe implements PipeTransform {
  transform(value: string): string {
    return value.replace(/\n/g, '<br>');
  }
}