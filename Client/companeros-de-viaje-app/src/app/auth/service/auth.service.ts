import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs';

const base_url = environment.base_url;

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  get token(): string {
    return localStorage.getItem('token') ?? '';
  }

  saveLocalStorage(token: string) {
    localStorage.clear();
    localStorage.setItem('token', token);
  }

  logout() {
    localStorage.removeItem('token');
  }

  login(formData: any) {
    return this.http.post(`${base_url}/api/auth/login`, formData).pipe(
      tap((resp: any) => {
        this.saveLocalStorage(resp.token);
      })
    );
  }

  register(formData: any) {
    return this.http.post(`${base_url}/api/auth/register`, formData).pipe(
      tap((resp: any) => {
        this.saveLocalStorage(resp.token);
      })
    );
  }
}
