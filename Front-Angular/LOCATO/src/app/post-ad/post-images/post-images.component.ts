import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-post-images',
  templateUrl: './post-images.component.html',
  styleUrls: ['./post-images.component.scss']
})
export class PostImagesComponent {
  imagesArr: any[] = [];
  formData: any
  location: { lat: number; lng: number } | undefined;

  constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient) {
    this.route.queryParams.subscribe((params) => {
      this.formData = params;
      console.log('Received Form Data in PostImagesComponent:', this.formData);
    });
  }

  onFileSelected(event: any): void {
    const files = event.target.files;
    this.imagesArr = [];

    if (files) {
      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const reader = new FileReader();

        reader.onload = (e: any) => {
          this.imagesArr.push({ url: e.target.result, file });
        };

        reader.readAsDataURL(file);
      }
    }
  }

  uploadData(formData: any): void {

    const fD = new FormData();

    for (const image of this.imagesArr) {
      fD.append('images', image.file, image.file.name);
    }

    for (const key in this.formData) {
      if (this.formData.hasOwnProperty(key)) {
        fD.append(key, this.formData[key]);
      }
    }
    this.http.post('http://localhost:8081/addAd', formData).subscribe({
      next: (response) => {
        console.log('Upload successful:', response);
        // Handle success, e.g., navigate to a success page
      },
      error: (error) => {
        console.error('Upload failed:', error);
        // Handle error, display error message, etc.
      },
      complete: () => {
        // Optionally handle the complete event
      },
    });
  }
}
