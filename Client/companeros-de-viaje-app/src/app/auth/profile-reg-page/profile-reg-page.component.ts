import { Component, OnInit } from '@angular/core';
import { Credential, RegisterService } from '../service/register.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormValidatorService } from '../service/form-validator.service';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { ToastService } from 'src/app/core/service/toast.service';
@Component({
  selector: 'app-profile-reg-page',
  templateUrl: './profile-reg-page.component.html',
  styleUrls: ['./profile-reg-page.component.scss'],
})
export class ProfileRegPageComponent implements OnInit {
  constructor(
    private registerService: RegisterService,
    private fb: FormBuilder,
    private formValidator: FormValidatorService,
    private authService: AuthService,
    private router: Router,
    private toastService: ToastService
  ) {}

  public pRegForm: FormGroup = this.fb.group({
    address: ['', [Validators.required, Validators.minLength(8)]],
    birthDate: ['', [Validators.required]],
    phoneNumber: ['', Validators.required],
    gender: ['', Validators.required],
    country: ['', Validators.required],
    interest: ['', Validators.required],
  });

  credential: Credential = {
    name: '',
    username: '',
    email: '',
    password: '',
  };

  genders: any[] = [{ name: 'HOMBRE' }, { name: 'MUJER' }];
  country: any[] = [{ name: 'Argentina' }];
  personal: any[] = [
    { name: 'Playas' },
    { name: 'Montañas' },
    { name: 'Rios' },
    { name: 'Eventos deportivos' },
    { name: 'Eventos musicales' },
    { name: 'turismo urbano' },
    { name: 'Turismo nocturno' },
  ];

  ngOnInit(): void {
    this.registerService.credential$.subscribe();
  }

  public get gCredential(): Credential {
    return this.registerService.gCredential;
  }

  isValidField(field: string) {
    return this.formValidator.isValidField(this.pRegForm, field);
  }

  onSubmit(): void {
    if (this.pRegForm.invalid) {
      this.pRegForm.markAllAsTouched();
      this.markDirty();
      return;
    }

    const body = {
      ...this.gCredential,
      ...this.pRegForm.value,
      role: 'ROLE_USER',
    };

    this.authService.register(body).subscribe({
      next: () => {
        this.pRegForm.reset();
        this.toastService.showToast(
          'success',
          'Service Message',
          'Registro exitoso.'
        );
        this.router.navigateByUrl('/book/home');
      },
      error: (err) => {
        this.toastService.showToast(
          'error',
          'Service Message',
          'Los datos ingresados no son válidos.'
        );
        this.router.navigateByUrl('/auth/new-account');
      },
    });
  }

  markDirty() {
    this.pRegForm.get('address')?.markAsDirty();
    this.pRegForm.get('birthDate')?.markAsDirty();
    this.pRegForm.get('phoneNumber')?.markAsDirty();
    this.pRegForm.get('gender')?.markAsDirty();
    this.pRegForm.get('country')?.markAsDirty();
    this.pRegForm.get('interest')?.markAsDirty();
  }
}
