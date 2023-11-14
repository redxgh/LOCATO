import { Component, Input } from '@angular/core';
import { Ad } from 'src/app/Ad';

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
    timestamp: "",
    accommodationId: 0,
    userId: 0,
    images:[],
  }
}
