import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'auth-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  constructor(private router: Router) {}

  onClick() {
    this.router.navigateByUrl('/cdv/home');
  }
}
