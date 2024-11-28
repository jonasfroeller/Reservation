import { PlaceType } from "./PlaceType";

export interface Place {
  id: number;
  place_type: PlaceType;
  location: string;
}
