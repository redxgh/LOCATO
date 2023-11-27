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
  formData: any;
  location: { lat: number; lng: number } | undefined;
  hoveredIndex: number | null = null;

  constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient) {
    this.route.queryParams.subscribe((params) => {
      this.formData = params;
      console.log('Received Form Data in PostImagesComponent:', this.formData);
    });
  }

  setHoveredIndex(index: number | null): void {
    this.hoveredIndex = index;
  }

  removeImage(index: number): void {
    this.imagesArr.splice(index, 1);}

  onFileSelected(event: any): void {
    const files = event.target.files;

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

  uploadData(): void {
    let fD = new FormData();

    for (let i = 0; i < this.imagesArr.length; i++) {
      fD.append('imagesArr', this.imagesArr[i].file);
    }

    for (const key in this.formData) {
      if (this.formData.hasOwnProperty(key)) {
        fD.append(key, this.formData[key]);
      }
    }

    this.http.post('http://localhost:8081/addAd', fD).subscribe({
      next: (response) => {
        console.log('Upload successful:', response);
      },
      error: (error) => {
        console.error('Upload failed:', error);
      },
      complete: () => {
      },
    });
  }
}
function removeImage(index: any, number: any) {
  throw new Error('Function not implemented.');
}

