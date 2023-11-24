import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import * as L from 'leaflet';

interface location {
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
  formData: any;
  location: { lat: number; lng: number } | undefined;

  constructor(private fb: FormBuilder, private router: Router, private route: ActivatedRoute) {
    this.route.queryParams.subscribe((params) => {
      this.formData = params;
      console.log('Received Form Data:', this.formData);
    });
  }

  onLocationSelected(location: { lat: number; lng: number }): void {
    this.location = location;
    console.log('Selected Location:', this.location);
  }

  navigateToNextComponent(): void {
    if (this.location) {
      const location = `${this.location.lat},${this.location.lng}`;

      const mergedData = { ...this.formData, location };
      console.log('Merged Data:', mergedData);

      this.router.navigate(['/post-images'], { queryParams: mergedData });
    } else {
      console.error('Location is not selected.');
    }
  }
}
