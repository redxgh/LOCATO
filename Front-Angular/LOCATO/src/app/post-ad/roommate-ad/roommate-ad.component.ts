import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-roommate-ad',
  templateUrl: './roommate-ad.component.html',
  styleUrls: ['./roommate-ad.component.scss']
})
export class RoommateAdComponent implements OnInit {
  form: FormGroup = this.fb.group({
    title: ['', Validators.required],
    price: [null, Validators.required],
    gender: ['', Validators.required],
    desc: ['', Validators.required],
  });

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit() {
    // Initialize your form controls here
    this.form = this.fb.group({
      title: ['', Validators.required],
      price: [null, Validators.required],
      gender: ['', Validators.required],
      desc: ['', Validators.required],
    });
  }

  goto() {
    if (this.form.valid) {
      FormData= this.form.value
      console.log('Form Data:', FormData);
      // Pass form data in the route state
      this.router.navigate(['/accomodation'],{ queryParams: FormData });
    }
  }
}
