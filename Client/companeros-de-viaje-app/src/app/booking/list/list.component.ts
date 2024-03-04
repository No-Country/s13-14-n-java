import { Component, OnInit } from '@angular/core';
import { BookingService } from '../service/booking.service';
import { Content } from 'src/app/core/interfaces/groupList.interface';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent implements OnInit {
  constructor(private bookingService: BookingService) {}

  groups: Content[] = [];

  destination: string = '';
  departureDate: string = '';
  returnDate: string = '';
  budget: string = '';

  interests: any[] = [
    { name: 'Playas' },
    { name: 'Montañas' },
    { name: 'Rios' },
    { name: 'Eventos deportivos' },
    { name: 'Eventos musicales' },
    { name: 'turismo urbano' },
    { name: 'Turismo nocturno' },
  ];

  ngOnInit(): void {
    this.getGroups();
  }

  getGroups() {
    this.bookingService.getGroups().subscribe({
      next: (groups) => {
        this.groups = groups.content;
      },
    });
  }

  onFilterGroups() {
    let spot = this.replaceNullOrUndefined(this.destination);
    let dDate = this.replaceNullOrUndefined(this.departureDate);
    let rDate = this.replaceNullOrUndefined(this.returnDate);
    let budget = this.replaceNullOrUndefined(this.budget);
    if (!this.isEmptyString(dDate)) {
      dDate = `${dDate}T00:00:00`;
    }
    if (!this.isEmptyString(rDate)) {
      rDate = `${rDate}T00:00:00`;
    }
    this.bookingService.filterGroups(spot, dDate, rDate, budget).subscribe({
      next: (groups) => {
        this.groups = groups.content;
      },
    });
  }

  isEmptyString(str: string): boolean {
    return str.trim() === '';
  }

  replaceNullOrUndefined(value: any): string {
    if (value === null || value === undefined) {
      return '';
    } else {
      return value;
    }
  }
}
