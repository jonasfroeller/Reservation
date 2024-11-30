import {ChangeDetectionStrategy, inject} from '@angular/core';
import {ReactiveFormsModule, FormBuilder, Validators, FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import {MatNativeDateModule, provideNativeDateAdapter} from '@angular/material/core';
import { ReservationService } from '../reservation.service';
import {Component} from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatTimepickerModule, MatTimepickerOption} from '@angular/material/timepicker';
import {MatFormFieldModule} from '@angular/material/form-field';

@Component({
  selector: 'app-reservation',
  providers: [provideNativeDateAdapter()],
  imports: [
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDatepickerModule,
    FormsModule,
    MatFormFieldModule,
    MatTimepickerModule
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './reservation.component.html',
  standalone: true,
  styleUrl: './reservation.component.css'
})
export class ReservationComponent {
  private fb = inject(FormBuilder);
  private reservationService = inject(ReservationService);

  // TODO: fetch available times from backend
  reservationStartTimeOptions: MatTimepickerOption<Date>[] = [
    {label: 'Morning', value: new Date(2024, 0, 1, 9, 0, 0)},
    {label: 'Noon', value: new Date(2024, 0, 1, 12, 0, 0)},
    {label: 'Evening', value: new Date(2024, 0, 1, 22, 0, 0)},
  ];

  // TODO: fetch available times from backend
  reservationEndTimeOptions: MatTimepickerOption<Date>[] = [
    {label: 'Morning', value: new Date(2024, 0, 1, 9, 0, 0)},
    {label: 'Noon', value: new Date(2024, 0, 1, 12, 0, 0)},
    {label: 'Evening', value: new Date(2024, 0, 1, 22, 0, 0)},
  ];

  reservationForm = this.fb.group({
    customer_id: [null, Validators.required],
    place_id: [null, Validators.required],
    reservation_start_date: [null, Validators.required],
    reservation_start_time: [null, Validators.required],
    reservation_end_date: [null, Validators.required],
    reservation_end_time: [null, Validators.required],
  });

  onSubmit(): void {
    if (this.reservationForm.valid) {
      // TODO: reduce code complexity by using "Timepicker integration with datepicker"
      // see https://material.angular.io/components/timepicker/examples#timepicker-datepicker-integration
      const reservation_start_date = new Date(this.reservationForm.value.reservation_start_date as unknown as string);
      const reservation_start_time = new Date(this.reservationForm.value.reservation_start_time as unknown as string);
      const reservation_end_date = new Date(this.reservationForm.value.reservation_end_date as unknown as string);
      const reservation_end_time = new Date(this.reservationForm.value.reservation_end_time as unknown as string);

      const reservation_start = new Date(
        reservation_start_date.getFullYear(),
        reservation_start_date.getMonth(),
        reservation_start_date.getDate(),
        reservation_start_time.getHours(),
        reservation_start_time.getMinutes(),
        reservation_start_time.getSeconds()
      );

      const reservation_end = new Date(
        reservation_end_date.getFullYear(),
        reservation_end_date.getMonth(),
        reservation_end_date.getDate(),
        reservation_end_time.getHours(),
        reservation_end_time.getMinutes(),
        reservation_end_time.getSeconds()
      );

      const reservationData = {
        customer: { id: this.reservationForm.value.customer_id as unknown as number },
        place: { id: this.reservationForm.value.place_id as unknown as number },
        reservation_start: reservation_start as unknown as Date,
        reservation_end: reservation_end as unknown as Date
      };

      /* console.log(reservationData);
      console.log(new Date(reservationData.reservation_start));
      console.log(new Date(reservationData.reservation_end)); */

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
