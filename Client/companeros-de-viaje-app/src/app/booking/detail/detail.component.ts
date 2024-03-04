import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { BookingService } from '../service/booking.service';
import { GroupResponse } from 'src/app/core/interfaces/groupResp.interface';
import { ToastService } from 'src/app/core/service/toast.service';
import { TokenService } from '../service/token.service';
import { Owner } from 'src/app/core/interfaces/groupList.interface';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss'],
})
export class DetailComponent implements OnInit {
  group?: GroupResponse;

  public displayJoin: boolean = false;
  public displayLeave: boolean = false;

  public showJoinBtn: boolean = false;
  public showEditBtn: boolean = false;
  public showLeaveBtn: boolean = false;

  public editId?: number;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private bookingService: BookingService,
    private toastService: ToastService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.searchById();
  }

  searchById() {
    this.activatedRoute.params
      .pipe(switchMap(({ id }) => this.bookingService.getDetailById(id)))
      .subscribe({
        next: (resp) => {
          this.editId = resp.id;
          this.group = resp;
          this.showJoinBtn = this.isUsernameInList(
            resp.travelers,
            this.username
          );
          this.showEditBtn = this.isOwner(resp.owner.username, this.username);
          this.showLeaveBtn = !this.showEditBtn && this.showJoinBtn;
        },
        error: () => {
          console.log('err');
        },
      });
  }

  onJoinGroup(id: number | undefined) {
    if (!id) return;
    this.bookingService.joinOnGroup(id).subscribe({
      next: (resp) => {
        this.toastService.showToast(
          'success',
          'Service Message',
          'Registro exitoso.'
        );
        this.displayJoin = false;

        this.searchById();
      },
    });
  }

  showJoinDialog() {
    this.displayJoin = true;
  }

  showLeaveDialog() {
    this.displayLeave = true;
  }

  onLeaveGroup(id: number | undefined) {
    if (!id) return;
    this.bookingService.leaveGroup(id).subscribe({
      next: (resp) => {
        this.toastService.showToast(
          'success',
          'Service Message',
          'Abandono exitoso.'
        );
        this.displayLeave = false;

        this.searchById();
      },
    });
  }

  public get username(): string {
    if (!sessionStorage.getItem('token')) return '';
    const token = sessionStorage.getItem('token')!;
    const payload = this.tokenService.decodeToken(token);
    const username = payload.sub;

    return username;
  }

  isUsernameInList(travelers: Owner[], username: string): boolean {
    return travelers.some((traveler) => traveler.username === username);
  }

  isOwner(owner: string, username: string): boolean {
    return owner === username;
  }
}
