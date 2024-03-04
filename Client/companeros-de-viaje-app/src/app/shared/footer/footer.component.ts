import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth/service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
})
export class FooterComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  public onSearch() {
    if (this.authService.hasToken) {
      this.router.navigateByUrl('/book/home');
    } else {
      this.router.navigateByUrl('/auth/login');
    }
  }
}
