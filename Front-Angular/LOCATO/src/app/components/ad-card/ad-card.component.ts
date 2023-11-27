import { Component, Input } from '@angular/core';
import { Ad } from '../../model/Ad';


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

}
