import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../service/register.service';
import { Router } from '@angular/router';
import { FormValidatorService } from '../service/form-validator.service';
import { Observer } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private registerService: RegisterService,
    private router: Router,
    private formValidator: FormValidatorService,
    private authService: AuthService
  ) {}

  public loginForm: FormGroup = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  ngOnInit(): void {
    sessionStorage.removeItem('token');
  }

  observer: Observer<any> = {
    next: (value) => {
      console.log('[next]:', value);
    },
    error: (error) => console.warn('[error]:', error),
    complete: () => console.warn('complete'),
  };

  isValidField(field: string) {
    return this.formValidator.isValidField(this.loginForm, field);
  }

  onLogin() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      this.markDirty();
      return;
    }
    console.log(this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe({
      next: () => {
        this.loginForm.reset();
        this.router.navigateByUrl('/book/home');
      },
      error: (error) => {
        this.loginForm.reset();
      },
    });
  }

  markDirty() {
    this.loginForm.get('username')?.markAsDirty();
    this.loginForm.get('password')?.markAsDirty();
  }
}
