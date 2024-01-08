import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { SecurityService } from 'src/app/services/security.service';
import { AdService } from 'src/app/services/ad.service';

@Component({
  selector: 'app-edit-roommate-ad',
  templateUrl: './edit-roommate-ad.component.html',
  styleUrls: ['./edit-roommate-ad.component.scss']
})
export class EditRoommateAdComponent implements OnInit {
  private agentProfile: any = {};
  adId!: string;
  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private securityService: SecurityService,
    private kc: KeycloakService,
    private adService: AdService
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.adId = params['adId'];

      this.kc.loadUserProfile().then((user) => {
        this.agentProfile = user;
        console.log(this.agentProfile.id);
 
        this.form = this.fb.group({
          title: ['', Validators.required],
          price: [null, Validators.required],
          // best: [1, Validators.required],
          description: ['', Validators.required],
          gender: ['Female', Validators.required],
          userId: [this.agentProfile.id, Validators.required],
        });

        if (this.adId) {
          this.adService.getAdById(this.adId).subscribe((adDetails) => {
            this.form.patchValue(adDetails);
          });
        }
      });
    });
  }

  goto() {
    if (this.form.valid) {
      const formData = this.form.value;
      console.log('Form Data:', formData);
      this.router.navigate(['/edit-accomodation-ad'], { queryParams: { ...formData, adId: this.adId } });
    }
  }
}
