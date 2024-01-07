import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { SecurityService } from 'src/app/services/security.service';

@Component({
  selector: 'app-roommate-ad',
  templateUrl: './roommate-ad.component.html',
  styleUrls: ['./roommate-ad.component.scss']
})
export class RoommateAdComponent implements OnInit {
  private agentProfile: any = {};
  form: FormGroup = this.fb.group({
    title: ['', Validators.required],
    price: [null, Validators.required],
    best: [1, Validators.required],
    description: ['', Validators.required],
  });

  constructor(private fb: FormBuilder, private router: Router,
    private securityService: SecurityService,
    private kc: KeycloakService) {
  }

  ngOnInit() {
    this.kc.loadUserProfile().then(user => {
      this.agentProfile = user;
      console.log(this.agentProfile.id)

      // Initialize your form controls here
    this.form = this.fb.group({
      title: ['', Validators.required],
      price: [null, Validators.required],
      best: [1, Validators.required],
      description: ['', Validators.required],
      userId: [this.agentProfile.id, Validators.required],
    });
    console.log(this.agentProfile.id)
  })
  }

  goto() {
    if (this.form.valid) {
      FormData= this.form.value
      console.log('Form Data:', FormData);
      this.router.navigate(['/accomodation'],{ queryParams: FormData });
    }
  }
}
