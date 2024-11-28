import { Routes } from '@angular/router';
import {ReservationsComponent} from './reservations/reservations.component';
import {CustomerComponent} from './customer/customer.component';
import {ReservationComponent} from './reservation/reservation.component';

export const routes: Routes = [
  {
    path: '',
    title: 'Kundendaten',
    component: CustomerComponent
  },
  {
    path: 'reservations',
    title: 'Reservierungsliste',
    component: ReservationsComponent
  },
  {
    path: 'reservation',
    title: 'Neue Reservierung',
    component: ReservationComponent
  }
];
