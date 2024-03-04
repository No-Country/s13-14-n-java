import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

export interface Credential {
  name: string;
  username: string;
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  private credential: Credential = {
    name: '',
    username: '',
    email: '',
    password: '',
  };

  credential$ = new Subject<Credential>();

  saveCredential(form: Credential) {
    this.credential = form;
    this.credential$.next(form);
  }

  public get gCredential(): Credential {
    return this.credential;
  }
}
