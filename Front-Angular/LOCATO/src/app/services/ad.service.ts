import { Injectable } from '@angular/core';
import { Ad } from '../model/Ad';

@Injectable({
  providedIn: 'root'
})
export class AdService {
  formData:any
  ads:Ad[];
  constructor() {
     this.ads = [
    {
      id: 1,
      title: 'Luxury Apartment in Downtown',
      description: 'Spacious 2-bedroom apartment with a great view.',
      price: 1500,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['house2.jpg', 'house2.jpg', 'house4.jpg']
    },
    {
      id: 2,
      title: 'Cozy Room in Shared House',
      description: 'A comfortable room in a friendly shared house.',
      price: 50,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['house3.jpg', 'house2.jpg', 'house4.jpg']
    },
    {
      id: 3,
      title: 'Roommate Wanted for Spacious Condo',
      description: 'Looking for a roommate to share a beautiful condo.',
      price: 800,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['hotel.jpg', 'house2.jpg', 'house4.jpg']
    },
    {
      id: 4,
      title: 'Charming Single Room for Rent',
      description: 'Single room available in a lovely house.',
      price: 60,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['default.jpg', 'house2.jpg', 'house4.jpg']
    },
    {
      id: 5,
      title: 'Spacious House with Garden',
      description: '3-bedroom house with a beautiful garden and pool.',
      price: 2000,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['images.jpg', 'house2.jpg', 'house4.jpg']
    },
    {
      id: 1,
      title: 'Luxury Apartment in Downtown',
      description: 'Spacious 2-bedroom apartment with a great view.',
      price: 1500,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['house1.jpg', 'house2.jpg', 'house4.jpg']
    },
    {
      id: 2,
      title: 'Cozy Room in Shared House',
      description: 'A comfortable room in a friendly shared house.',
      price: 50,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['house3.jpg', 'house2.jpg', 'house4.jpg']
    },
    {
      id: 3,
      title: 'Roommate Wanted for Spacious Condo',
      description: 'Looking for a roommate to share a beautiful condo.',
      price: 800,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['house2.jpg', 'house1.jpg', 'house4.jpg']
    },
    {
      id: 4,
      title: 'Charming Single Room for Rent',
      description: 'Single room available in a lovely house.',
      price: 60,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['house4.jpg', 'house2.jpg', 'house4.jpg']
    },
    {
      id: 5,
      title: 'Spacious House with Garden',
      description: '3-bedroom house with a beautiful garden and pool.',
      price: 2000,
      surface:502,
      rooms:4,
      bathrooms:5,
      type:'villa',
      category:'category1',
      location:':skhdlksqdkj5s6dqdmlkqnskjcv',
      best:1,
      imagesArr: ['yujiro.jpg', 'house2.jpg', 'house4.jpg']
    },
  ];
  }
  listAds():Ad[]{
    return this.ads;
  }
}
