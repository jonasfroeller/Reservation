import { Component } from '@angular/core';
import {CustomerComponent} from '../customer/customer.component';
import {ReservationsComponent} from '../reservations/reservations.component';
import { ReservationComponent } from "../reservation/reservation.component";

@Component({
  selector: 'app-dashboard',
  imports: [
    CustomerComponent,
    ReservationsComponent,
    ReservationComponent
],
  templateUrl: './dashboard.component.html',
  standalone: true,
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
