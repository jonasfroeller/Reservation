import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {MenuComponent} from './menu/menu.component';
import {CustomerComponent} from './customer/customer.component';

@Component({
    selector: 'app-root',
    imports: [RouterOutlet, MenuComponent, CustomerComponent],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent {
}
