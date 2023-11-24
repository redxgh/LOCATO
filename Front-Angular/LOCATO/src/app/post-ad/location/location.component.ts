import { Component, EventEmitter, Output } from '@angular/core';
import * as L from 'leaflet';

interface Coordinates {
  address: string;
  latitude: number;
  longitude: number;
}

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.scss']
})
export class LocationComponent {
   // Handle the selected location from the map
   onLocationSelected(location: { lat: number; lng: number }): void {
    // Use the selected location as needed (e.g., save to form model)
    console.log('Selected Location:', location);
  }

}
