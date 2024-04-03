import { Pipe, PipeTransform } from '@angular/core';
import { FeedbackGiven } from './models/Feedback';

@Pipe({
  name: 'enumToString'
})
export class EnumToStringPipe implements PipeTransform {
  transform(value: any): string {
    if (typeof value === 'string' || typeof value === 'number') {
      return FeedbackGiven[value as keyof typeof FeedbackGiven] || '';
    }
    return '';
  }
}
