import { Routes } from '@angular/router';
import {ReservationsComponent} from './components/reservations/reservations.component';
import {CustomerComponent} from './components/customer/customer.component';
import {ReservationComponent} from './components/reservation/reservation.component';

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
