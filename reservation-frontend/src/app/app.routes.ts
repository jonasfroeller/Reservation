import { Routes } from '@angular/router';
import {ReservationsComponent} from './reservations/reservations.component';
import {CustomerComponent} from './customer/customer.component';
import {DashboardComponent} from './dashboard/dashboard.component';

export const routes: Routes = [
  {
    path: '',
    title: 'Home',
    component: DashboardComponent
  },
  {
    path: 'customers',
    title: 'Customers',
    component: CustomerComponent
  },
  {
    path: 'reservations',
    title: 'Reservations',
    component: ReservationsComponent
  }
];
