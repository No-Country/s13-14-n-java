import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GroupBody } from 'src/app/core/interfaces/groupBody.interface';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { GroupList } from 'src/app/core/interfaces/groupList.interface';
import { GroupResponse } from 'src/app/core/interfaces/groupResp.interface';

const base_url = environment.base_url;

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  constructor(private http: HttpClient) {}

  createGroup(formData: GroupBody) {
    return this.http.post(`${base_url}/travel-group/create`, formData);
  }
  // travel-group/find-travel-group?page=0&size=15&sort=id,DESC
  getGroups(): Observable<GroupList> {
    const params = new HttpParams()
      .set('page', '')
      .set('size', '')
      .set('sort', 'id,DESC');
    return this.http.get<GroupList>(
      `${base_url}/travel-group/find-travel-group`,
      {
        params,
      }
    );
  }

  filterGroups(
    destination: string,
    departureDate: string,
    returnDate: string,
    budget: string
  ): Observable<GroupList> {
    const params = new HttpParams()
      .set('page', '')
      .set('size', '')
      .set('sort', 'id,DESC')
      .set('destination', destination)
      .set('departureDate', departureDate)
      .set('returnDate', returnDate)
      .set('budget', budget);

    return this.http.get<GroupList>(
      `${base_url}/travel-group/find-travel-group`,
      {
        params,
      }
    );
  }

  // travel-group/find-travel-group-no-login
  getGroupsWithoutTk(): Observable<GroupList> {
    return this.http.get<GroupList>(
      `${base_url}/travel-group/find-travel-group-no-login`
    );
  }

  // /travel-group/findByOwner
  getOwnerGroups(): Observable<GroupList> {
    return this.http.get<GroupList>(`${base_url}/travel-group/findByOwner`);
  }

  getDetailById(id: number): Observable<GroupResponse> {
    return this.http.get<GroupResponse>(`${base_url}/travel-group/${id}`);
  }

  // travel-group/add-user?groupId=9

  joinOnGroup(id: number): Observable<GroupResponse> {
    const params = new HttpParams().set('groupId', `${id}`);

    return this.http.post<GroupResponse>(
      `${base_url}/travel-group/add-user`,
      null,
      { params: params }
    );
  }
  // /travel-group/leave-travel-group?groupId=10'
  leaveGroup(id: number) {
    const params = new HttpParams().set('groupId', `${id}`);
    return this.http.put<GroupResponse>(
      `${base_url}/travel-group/leave-travel-group`,
      null,
      { params: params }
    );
  }
  // /travel-group/update-travel-group?groupId=1
  updateGroup(id: number, formData: GroupBody): Observable<GroupResponse> {
    const params = new HttpParams().set('groupId', `${id}`);
    return this.http.put<GroupResponse>(
      `${base_url}/travel-group/update-travel-group`,
      formData,
      { params: params }
    );
  }
}
