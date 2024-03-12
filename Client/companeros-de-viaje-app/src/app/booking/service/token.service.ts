import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  decodeToken(token: string): any {
    const parts = token.split('.');
    const decodedPayload = JSON.parse(atob(parts[1]));
    return decodedPayload;
  }

  extractAuthority(token: string): string | null {
    const decodedPayload = this.decodeToken(token);
    const role = decodedPayload.role[0];
    if (role?.authority) {
      return role.authority;
    } else {
      return null;
    }
  }
}
