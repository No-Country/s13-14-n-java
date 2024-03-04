import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormValidatorService } from '../../auth/service/form-validator.service';
import { GroupBody } from 'src/app/core/interfaces/groupBody.interface';
import { BookingService } from '../service/booking.service';
import { ToastService } from '../../core/service/toast.service';

interface InterestItem {
  name: string;
}

@Component({
  selector: 'app-new-group',
  templateUrl: './new-group.component.html',
  styleUrls: ['./new-group.component.scss'],
})
export class NewGroupComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private formValidator: FormValidatorService,
    private bookService: BookingService,
    private toastService: ToastService
  ) {}

  body: GroupBody = {
    destination: '',
    departureDate: '',
    returnDate: '',
    itinerary: '',
    budget: 0,
    interests: [''],
    minimumNumberOfMembers: 0,
  };

  interests: InterestItem[] = [
    { name: 'Playas' },
    { name: 'Montañas' },
    { name: 'Rios' },
    { name: 'Eventos deportivos' },
    { name: 'Eventos musicales' },
    { name: 'turismo urbano' },
    { name: 'Turismo nocturno' },
  ];

  public newGrpForm: FormGroup = this.fb.group({
    spot: ['', [Validators.required]],
    iDate: ['', [Validators.required]],
    fDate: ['', [Validators.required]],
    budget: ['', [Validators.required]],
    people: ['', [Validators.required]],
    interest: ['', [Validators.required]],
    plan: ['', [Validators.required]],
  });

  ngOnInit(): void {}

  isValidField(field: string) {
    return this.formValidator.isValidField(this.newGrpForm, field);
  }

  onSubmit(): void {
    if (this.newGrpForm.invalid) {
      this.newGrpForm.markAllAsTouched();
      this.markDirty();
      return;
    }

    const { spot, iDate, fDate, budget, people, interest, plan } =
      this.newGrpForm.value;

    const interests: string[] = interest.map((item: InterestItem) => item.name);

    this.body.destination = spot;
    this.body.departureDate = `${iDate}T00:00:00`;
    this.body.returnDate = `${fDate}T00:00:00`;
    this.body.budget = budget;
    this.body.minimumNumberOfMembers = people;
    this.body.itinerary = plan;
    this.body.interests = interests;

    this.bookService.createGroup(this.body).subscribe({
      next: () => {
        this.newGrpForm.reset();
        this.toastService.showToast(
          'success',
          'Service Message',
          '¡Excelente! Tu formulario ha sido registrado satisfactoriamente.'
        );
        this.router.navigateByUrl('/book/home');
      },
      error: (err) => {
        this.newGrpForm.reset();
        this.toastService.showToast(
          'error',
          'Service Message',
          'Los datos ingresados no son válidos.'
        );
      },
    });
  }

  markDirty() {
    this.newGrpForm.get('spot')?.markAsDirty();
    this.newGrpForm.get('iDate')?.markAsDirty();
    this.newGrpForm.get('fDate')?.markAsDirty();
    this.newGrpForm.get('budget')?.markAsDirty();
    this.newGrpForm.get('people')?.markAsDirty();
    this.newGrpForm.get('interest')?.markAsDirty();
    this.newGrpForm.get('plan')?.markAsDirty();
  }
}
