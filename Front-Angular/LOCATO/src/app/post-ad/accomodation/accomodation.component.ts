import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-accomodation',
  templateUrl: './accomodation.component.html',
  styleUrls: ['./accomodation.component.scss']
})
export class AccomodationComponent {
  formData: any;
  numbers: number[] = Array.from({ length: 11 }, (_, i) => i);
  categories: number[] = [1, 2, 3]
  types: string[] = ['villa', 'appartement', 'cave']

  form: FormGroup = this.fb.group({
    surface: [null, Validators.required],
    rooms: [null, Validators.required],
    bathrooms: [null, Validators.required],
    categoryId: [null, Validators.required],
    type: ['', Validators.required],
    best: [1, Validators.required],
  });

  constructor(private fb: FormBuilder, private router: Router, private route: ActivatedRoute) {
    this.route.queryParams.subscribe((params) => {
      this.formData = params;
      console.log('Received Data:', this.formData);
    });
  }

  ngOnInit() {
    this.form = this.fb.group({
      surface: [null, Validators.required],
      rooms: [null, Validators.required],
      bathrooms: [null, Validators.required],
      categoryId: [null, Validators.required],
      type: ['', Validators.required],
      best: [1, Validators.required],
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
