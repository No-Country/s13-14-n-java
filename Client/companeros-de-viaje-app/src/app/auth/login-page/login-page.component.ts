import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../service/register.service';
import { Router } from '@angular/router';
import { FormValidatorService } from '../service/form-validator.service';

import { AuthService } from '../service/auth.service';
import { ToastService } from '../../core/service/toast.service';

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
    private authService: AuthService,
    private toastService: ToastService
  ) {}

  public loginForm: FormGroup = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  ngOnInit(): void {
    sessionStorage.removeItem('token');
  }

  isValidField(field: string) {
    return this.formValidator.isValidField(this.loginForm, field);
  }

  onLogin() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      this.markDirty();
      return;
    }

    this.authService.login(this.loginForm.value).subscribe({
      next: () => {
        this.loginForm.reset();
        this.toastService.showToast(
          'success',
          'Service Message',
          'Inicio de sesión exitoso.'
        );
        this.router.navigateByUrl('/book/home');
      },
      error: (error) => {
        this.loginForm.reset();
        this.toastService.showToast(
          'error',
          'Service Message',
          'Los datos ingresados no son válidos.'
        );
      },
    });
  }

  markDirty() {
    this.loginForm.get('username')?.markAsDirty();
    this.loginForm.get('password')?.markAsDirty();
  }
}
