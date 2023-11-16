import { Injectable } from '@angular/core';
import { Ad } from './model/Ad';

@Injectable({
  providedIn: 'root'
})
export class AdService {

  ads:Ad[];
  constructor() { 
     this.ads = [
    {
      id: 1,
      title: 'Luxury Apartment in Downtown',
      description: 'Spacious 2-bedroom apartment with a great view.',
      price: 1500,
      timestamp: 'month',
      accommodationId: 101,
      userId: 201,
      images: ['house3.jpg', 'house2.jpg', 'house4.jpg'],
    },
    {
      id: 2,
      title: 'Cozy Room in Shared House',
      description: 'A comfortable room in a friendly shared house.',
      price: 50,
      timestamp: 'night',
      accommodationId: 102,
      userId: 202,
      images: ['house4.jpg', 'house3.jpg', 'house4.jpg'],
    },
    {
      id: 3,
      title: 'Roommate Wanted for Spacious Condo',
      description: 'Looking for a roommate to share a beautiful condo.',
      price: 800,
      timestamp: 'month',
      accommodationId: 103,
      userId: 203,
      images: ['house3.jpg', 'house2.jpg', 'house4.jpg'],
    },
    {
      id: 4,
      title: 'Charming Single Room for Rent',
      description: 'Single room available in a lovely house.',
      price: 60,
      timestamp: 'night',
      accommodationId: 104,
      userId: 204,
      images: ['house1.jpg', 'house3.jpg', 'house4.jpg'],
    },
    {
      id: 5,
      title: 'Spacious House with Garden',
      description: '3-bedroom house with a beautiful garden and pool.',
      price: 2000,
      timestamp: 'month',
      accommodationId: 105,
      userId: 205,
      images: ['house2.jpg', 'house4.jpg'],
    },
    {
      id: 1,
      title: 'Luxury Apartment in Downtown',
      description: 'Spacious 2-bedroom apartment with a great view.',
      price: 1500,
      timestamp: 'month',
      accommodationId: 101,
      userId: 201,
      images: ['house4.jpg', 'house4.jpg'],
    },
    {
      id: 2,
      title: 'Cozy Room in Shared House',
      description: 'A comfortable room in a friendly shared house.',
      price: 50,
      timestamp: 'night',
      accommodationId: 102,
      userId: 202,
      images: ['house1.jpg', 'house2.jpg'],
    },
    {
      id: 3,
      title: 'Roommate Wanted for Spacious Condo',
      description: 'Looking for a roommate to share a beautiful condo.',
      price: 800,
      timestamp: 'month',
      accommodationId: 103,
      userId: 203,
      images: ['house1.jpg', 'house2.jpg', 'house3.jpg', 'house4.jpg'],
    },
    {
      id: 4,
      title: 'Charming Single Room for Rent',
      description: 'Single room available in a lovely house.',
      price: 60,
      timestamp: 'night',
      accommodationId: 104,
      userId: 204,
      images: ['house1.jpg', 'house2.jpg'],
    },
    {
      id: 5,
      title: 'Spacious House with Garden',
      description: '3-bedroom house with a beautiful garden and pool.',
      price: 2000,
      timestamp: 'month',
      accommodationId: 105,
      userId: 205,
      images: ['house2.jpg'],
    },
  ];
  }
  listAds():Ad[]{
    return this.ads;
  }
}
