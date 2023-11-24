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
    imagesArr: [],
    surface: 0,
    rooms: 0,
    bathrooms: 0,
    type: '',
    category: '',
    location: '',
    best: 0,

  }
}
