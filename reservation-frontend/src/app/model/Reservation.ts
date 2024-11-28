import { Customer } from "./Customer";
import { Place } from "./Place";

export interface CreateReservationDto {
  customer: { id: number };
  place: { id: number };
  reservation_start: Date;
  reservation_end: Date;
}

export interface Reservation {
  id: number;
  customer: Customer;
  place: Place;
  reservation_start: Date;
  reservation_end: Date;
}
