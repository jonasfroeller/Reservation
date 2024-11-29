import { Component } from '@angular/core';
import {CustomerComponent} from '../customer/customer.component';
import {ReservationsComponent} from '../reservations/reservations.component';

@Component({
    selector: 'app-dashboard',
    imports: [
        CustomerComponent,
        ReservationsComponent
    ],
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
