import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs';
import { TokenService } from '../../booking/service/token.service';

const base_url = environment.base_url;

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private tokenService: TokenService) {}

  get token(): string {
    return sessionStorage.getItem('token') ?? '';
  }

  public get hasToken(): boolean {
    if (sessionStorage.getItem('token')) {
      return true;
    }
    return false;
  }

  public get isAdmin(): boolean {
    const token = sessionStorage.getItem('token');
    if (!token) return false;
    const code = this.tokenService.extractAuthority(token);
    if (code === 'ROLE_ADMIN') return true;
    return false;
  }

  saveLocalStorage(token: string) {
    sessionStorage.clear();
    sessionStorage.setItem('token', token);
  }

  logout() {
    sessionStorage.removeItem('token');
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
