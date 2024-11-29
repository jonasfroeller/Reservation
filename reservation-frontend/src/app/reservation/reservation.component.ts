import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { ReservationService } from '../reservation.service';

@Component({
  selector: 'app-reservation',
  imports: [
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule
  ],
  templateUrl: './reservation.component.html',
  standalone: true,
  styleUrl: './reservation.component.css'
})
export class ReservationComponent {
  private fb = inject(FormBuilder);
  private reservationService = inject(ReservationService);

  reservationForm = this.fb.group({
    customer_id: [null, Validators.required],
    place_id: [null, Validators.required],
    reservation_start: [null, Validators.required],
    reservation_end: [null, Validators.required]
  });

  onSubmit(): void {
    if (this.reservationForm.valid) {
      const reservationData = {
        customer: { id: this.reservationForm.value.customer_id as unknown as number },
        place: { id: this.reservationForm.value.place_id as unknown as number },
        reservation_start: this.reservationForm.value.reservation_start as unknown as Date,
        reservation_end: this.reservationForm.value.reservation_end as unknown as Date
      };

      this.reservationService.createReservation(reservationData).subscribe({
        next: (response) => {
          console.log('Success:', response);
          this.reservationForm.reset();
        },
        error: (error) => {
          console.error('Error:', error);
        }
      });
    }
  }
}
