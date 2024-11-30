import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation } from '../model/Reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {
  private apiUrl = 'http://localhost:8080/api/reservations';

  constructor(private http: HttpClient) { }

  getReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.apiUrl);
  }
}
