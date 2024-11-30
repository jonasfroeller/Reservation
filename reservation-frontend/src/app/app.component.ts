import { Component } from '@angular/core';
import {MenuComponent} from './components/menu/menu.component';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [MenuComponent],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent {
  title = "Reservierungs-Tool"
}
