import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateReservationDto, Reservation } from '../model/Reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl = 'http://localhost:8080/reservations';

  constructor(private http: HttpClient) { }

  createReservation(reservation: CreateReservationDto): Observable<Reservation> {
    return this.http.post<Reservation>(this.apiUrl, reservation);
  }
}
