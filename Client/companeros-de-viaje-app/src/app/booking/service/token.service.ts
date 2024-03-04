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
}
