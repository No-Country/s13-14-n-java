import { Component, OnInit } from '@angular/core';
import { ListService } from '../service/list.service';
import { ReadyContent } from 'src/app/core/interfaces/readyGroup.interface';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  public groups: ReadyContent[] = [];
  public displayJoin: boolean = false;
  public id?: number;

  constructor(
    private listService: ListService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.getGroups();
  }

  getGroups() {
    this.listService.getReadyGroups().subscribe({
      next: (resp) => {
        this.groups = resp.content;
      },
    });
  }

  showJoinDialog(id: number) {
    console.log(id);

    this.id = id;
    this.displayJoin = true;
  }

  onJoinGroup() {
    if (!this.id) return;

    this.listService.confirmGroups(this.id).subscribe({
      next: () => {
        this.displayJoin = false;
        this.toastService.showToast(
          'success',
          'Service Message',
          'Registro exitoso.'
        );
        this.getGroups();
      },
    });
  }
}
