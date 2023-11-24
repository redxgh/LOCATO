import { AfterViewInit, Component, EventEmitter, Output } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements AfterViewInit {
  @Output() locationSelected = new EventEmitter<{ lat: number; lng: number }>();

  private map!: L.Map;
  private marker: L.Marker | null = null;

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.initializeMap();
    });
  }

  private initializeMap(): void {
    this.map = L.map('map').setView([51.505, -0.09], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {

    }).addTo(this.map);

    this.map.on('click', (event: L.LeafletMouseEvent) => {
      const selectedLocation = {
        lat: event.latlng.lat,
        lng: event.latlng.lng,
      };

      // Emit the selected location
      this.locationSelected.emit(selectedLocation);

      // Add or move the marker to the selected location
      this.addOrUpdateMarker(selectedLocation);
    });
  }

  private addOrUpdateMarker(location: { lat: number; lng: number }): void {
    const markerIcon = L.icon({
      iconUrl: 'assets/svgs/loc.png',
      iconSize: [32, 32],
      iconAnchor: [16, 32],
      popupAnchor: [0, -32],
    });

    if (this.marker) {
      this.marker.setLatLng([location.lat, location.lng]);
    } else {
      this.marker = L.marker([location.lat, location.lng], { icon: markerIcon }).addTo(this.map);
    }
  }
}
