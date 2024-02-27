import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Subject } from 'rxjs';
import { Toast } from '../../interfaces/toast.interface';
import { ToastService } from '../../service/toast.service';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.scss'],
  providers: [MessageService],
})
export class ToastComponent implements OnInit {
  constructor(
    private messageService: MessageService,
    private toastService: ToastService,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  toast$: Subject<Toast> = this.toastService.toast$;

  isShow: boolean = false;

  ngOnInit(): void {
    this.toast$.subscribe((toast) => {
      this.messageService.add({
        severity: toast.severity,
        summary: toast.summary,
        detail: toast.detail,
      });
      this.changeDetectorRef.detectChanges();
    });
  }
}
