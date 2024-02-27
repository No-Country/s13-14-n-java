import { Injectable } from '@angular/core';
import { Toast } from '../interfaces/toast.interface';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  toast: Toast = {
    summary: '',
    detail: '',
    severity: '',
  };

  toast$ = new Subject<Toast>();
  constructor() {}

  showToast(severity: string = '', summary: string = '', detail: string = '') {
    this.toast.detail = detail;
    this.toast.summary = summary;
    this.toast.severity = severity;
    this.toast$.next(this.toast);
    console.log('toast service');
  }
}
