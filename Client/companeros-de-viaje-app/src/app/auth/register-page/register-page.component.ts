import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Credential, RegisterService } from '../service/register.service';
import { Router } from '@angular/router';
import { FormValidatorService } from '../service/form-validator.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss'],
})
export class RegisterPageComponent {
  constructor(
    private fb: FormBuilder,
    private registerService: RegisterService,
    private router: Router,
    private formValidator: FormValidatorService
  ) {}

  credential: Credential = {
    name: '',
    username: '',
    email: '',
    password: '',
  };

  public registerForm: FormGroup = this.fb.group(
    {
      name: [
        '',
        [
          Validators.required,
          Validators.pattern(this.formValidator.firstNameAndLastNamePattern),
        ],
      ],
      username: ['', [Validators.required]],
      email: [
        '',
        [
          Validators.required,
          Validators.pattern(this.formValidator.emailPattern),
        ],
      ],
      pwd: ['', [Validators.required, Validators.minLength(8)]],
      pwd2: ['', [Validators.required, Validators.minLength(8)]],
    },
    {
      validators: [this.formValidator.isFieldOneEqualFieldTwo('pwd', 'pwd2')],
    }
  );

  isValidField(field: string) {
    return this.formValidator.isValidField(this.registerForm, field);
  }

  onRegister() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      this.markDirty();
      return;
    }

    console.log(this.registerForm.value);
    const { name, username, email, pwd } = this.registerForm.value;
    this.credential.name = name;
    this.credential.email = email;
    this.credential.password = pwd;
    this.credential.username = username;
    this.registerService.saveCredential(this.credential);
    this.router.navigateByUrl('auth/profile-register');
  }

  markDirty() {
    this.registerForm.get('name')?.markAsDirty();
    this.registerForm.get('username')?.markAsDirty();
    this.registerForm.get('email')?.markAsDirty();
    this.registerForm.get('pwd')?.markAsDirty();
    this.registerForm.get('pwd2')?.markAsDirty();
  }
}
