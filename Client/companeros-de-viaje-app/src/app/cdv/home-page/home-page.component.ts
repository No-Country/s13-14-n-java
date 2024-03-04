import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/service/auth.service';
import { BookingService } from 'src/app/booking/service/booking.service';
import { Content } from 'src/app/core/interfaces/groupList.interface';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
})
export class HomePageComponent implements OnInit {
  groups: Content[] = [];

  constructor(
    private bookingService: BookingService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getGroups();
  }

  getGroups() {
    this.bookingService.getGroupsWithoutTk().subscribe({
      next: (groups) => {
        this.groups = groups.content;
      },
    });
  }

  public onShowDetail(id: number) {
    if (this.authService.hasToken) {
      this.router.navigateByUrl(`/book/detail/${id}`);
    } else {
      this.router.navigateByUrl('/auth/login');
    }
  }
}

// [routerLink]="'/book/detail/' + item.id"
