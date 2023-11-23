import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-accomodation',
  templateUrl: './accomodation.component.html',
  styleUrls: ['./accomodation.component.scss']
})
export class AccomodationComponent {
  formData: any; // Define the type of your form data

  constructor(private route: ActivatedRoute) {
    this.route.queryParams.subscribe((params) => {
      this.formData = params;
      console.log('Received Data:', this.formData);
    });
  }
}
