import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConfirmGroup } from 'src/app/core/interfaces/confirmGroup.interface';
import { ReadyGroup } from 'src/app/core/interfaces/readyGroup.interface';
import { environment } from 'src/environments/environment';
const base_url = environment.base_url;
@Injectable({
  providedIn: 'root',
})
export class ListService {
  constructor(private http: HttpClient) {}

  getReadyGroups(): Observable<ReadyGroup> {
    return this.http.get<ReadyGroup>(
      `${base_url}/travel-group/find-completes-travel-groups`
    );
  }

  // travel-group/mark-as-negotiated?groupId=1
  confirmGroups(id: number) {
    return this.http.put(
      `${base_url}/travel-group/mark-as-negotiated?groupId=${id}`,
      null
    );
  }
}
