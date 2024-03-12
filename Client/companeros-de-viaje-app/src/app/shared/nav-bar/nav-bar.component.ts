import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth/service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss'],
})
export class NavBarComponent implements OnInit {
  public hasToke: boolean = false;

  public displayLogout: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  public get showLogoutBtn(): boolean {
    return this.authService.hasToken;
  }

  public get showDashboardBtn(): boolean {
    return this.authService.isAdmin;
  }

  public onSearch() {
    if (this.authService.hasToken) {
      this.router.navigateByUrl('/book/home');
    } else {
      this.router.navigateByUrl('/auth/login');
    }
  }

  showLogoutDialog() {
    this.displayLogout = true;
  }

  onLogoutDialog() {
    sessionStorage.clear();
    this.displayLogout = false;
    this.router.navigateByUrl('/cdv/home');
  }
}
