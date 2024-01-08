import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { SecurityService } from 'src/app/services/security.service';
import { AdService } from 'src/app/services/ad.service';

@Component({
  selector: 'app-edit-accomodation',
  templateUrl: './edit-accomodation.component.html',
  styleUrls: ['./edit-accomodation.component.scss']
})
export class EditAccomodationComponent {
  private agentProfile: any = {};
  formData: any;
  numbers: number[] = Array.from({ length: 11 }, (_, i) => i);
  categories: number[] = [1, 2, 3];
  types: string[] = ['villa', 'appartement', 'cave'];
  form!: FormGroup;
  adId!: string;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private securityService: SecurityService,
    private kc: KeycloakService,
    private adService: AdService
  ) {
    this.route.queryParams.subscribe((params) => {
      this.formData = params;
      console.log('Received Data:', this.formData);
    });
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.adId = params['adId'];
      console.log(this.adId);

      this.kc.loadUserProfile().then((user) => {
        this.agentProfile = user;
        console.log(this.agentProfile.id);

        // Initialize your form controls here
        this.form = this.fb.group({
          surface: [1, Validators.required],
          rooms: [1, Validators.required],
          bathrooms: [1, Validators.required],
          categoryId: [1, Validators.required],
          type: ['cave', Validators.required],
          best: [1, Validators.required],
          userId: [this.agentProfile.id, Validators.required], // Assuming userId is part of the form
        });

        if (this.adId) {
          // Fetch ad details and update the form for modification
          this.adService.getAdById(this.adId).subscribe((adDetails) => {
            console.log("aaaaaaaaaaaaaaaaaaaaaaaaa")
            console.log(adDetails.accomodation);

            this.form.patchValue({
              surface:adDetails.accomodation.surface,
              rooms:adDetails.accomodation.rooms
            });
          });
        }
      });
    });
  }

  mergeForms() {
    if (this.form.valid) {
      this.formData = { ...this.formData, ...this.form.value };

      console.log('Merged Form Data:', this.formData);
      this.router.navigate(['/location'], { queryParams: this.formData });
    }
  }
}

