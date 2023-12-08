import { Component, Input } from '@angular/core';
import { Ad } from '../../model/Ad';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';


@Component({
  selector: 'app-ad-card',
  templateUrl: './ad-card.component.html',
  styleUrls: ['./ad-card.component.scss']
})
export class AdCardComponent {

  @Input() ad: Ad = {
    id: 0,
    title: "",
    description: "",
    price: 0,
    surface: 0,
    accomodation: {
      images: [],
      rooms: 0,
      bathrooms: 0,
      type: '',
      category: '',
      location: '',
      best: 0,
    }
  }


  extractFileName(filePath: string): string {
    const lastSlashIndex = Math.max(filePath.lastIndexOf('/'));
    if (lastSlashIndex !== -1) {
      console.log(filePath.substring(lastSlashIndex + 1))
      return filePath.substring(lastSlashIndex + 1);
    } else {
      return filePath;
    }
  }

//testing
 imagePathPrefix = 'A:/Integration project/LOCATO/Microservices/ad-service/src/main/resources/static/images/';
 imageUrlPrefix = 'http://localhost:8081/images/';

 getImage(imagePath: string): Observable<SafeUrl> {
  const imageName = imagePath.replace(this.imagePathPrefix, '');
  const imageUrl = `${this.imageUrlPrefix}${imageName.startsWith('/') ? imageName.substring(1) : imageName}`;
  return new Observable<SafeUrl>((observer) => {
    observer.next(imageUrl as SafeUrl);
    observer.complete();
  });
}


}
