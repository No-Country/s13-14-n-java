import { Component, OnInit } from '@angular/core';
import { BookingService } from '../service/booking.service';
import { Content } from 'src/app/core/interfaces/groupList.interface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(private bookingService: BookingService) {}

  groups: Content[] = [];

  ngOnInit(): void {
    this.getGroups();
  }

  getGroups() {
    this.bookingService.getOwnerGroups().subscribe({
      next: (groups) => {
        this.groups = groups.content;
      },
    });
  }
}
