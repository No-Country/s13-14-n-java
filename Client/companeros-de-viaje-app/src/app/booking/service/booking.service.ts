import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GroupBody } from 'src/app/core/interfaces/groupBody.interface';
import { environment } from 'src/environments/environment';

const base_url = environment.base_url;

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  constructor(private http: HttpClient) {}

  createGroup(formData: GroupBody) {
    return this.http.post(`${base_url}/travel-group/create`, formData);
  }
}
