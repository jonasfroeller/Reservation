import { Component } from '@angular/core';
import {MenuComponent} from './menu/menu.component';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [MenuComponent],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent {
}
