import { Component } from '@angular/core';
import { Ad } from 'src/app/Ad';

@Component({
  selector: 'app-list-ads',
  templateUrl: './list-ads.component.html',
  styleUrls: ['./list-ads.component.scss']
})
export class ListAdsComponent {
  ads: Ad[] = [
    {
      id: 1,
      title: "Luxury Apartment in Downtown",
      description: "Spacious 2-bedroom apartment with a great view.",
      price: 1500,
      timestamp: "month",
      accommodationId: 101,
      userId: 201,
    },
    {
      id: 2,
      title: "Cozy Room in Shared House",
      description: "A comfortable room in a friendly shared house.",
      price: 50,
      timestamp: "night",
      accommodationId: 102,
      userId: 202,
    },
    {
      id: 3,
      title: "Roommate Wanted for Spacious Condo",
      description: "Looking for a roommate to share a beautiful condo.",
      price: 800,
      timestamp: "month",
      accommodationId: 103,
      userId: 203,
    },
    {
      id: 4,
      title: "Charming Single Room for Rent",
      description: "Single room available in a lovely house.",
      price: 60,
      timestamp: "night",
      accommodationId: 104,
      userId: 204,
    },
    {
      id: 5,
      title: "Spacious House with Garden",
      description: "3-bedroom house with a beautiful garden and pool.",
      price: 2000,
      timestamp: "month",
      accommodationId: 105,
      userId: 205,
    },
    {
      id: 1,
      title: "Luxury Apartment in Downtown",
      description: "Spacious 2-bedroom apartment with a great view.",
      price: 1500,
      timestamp: "month",
      accommodationId: 101,
      userId: 201,
    },
    {
      id: 2,
      title: "Cozy Room in Shared House",
      description: "A comfortable room in a friendly shared house.",
      price: 50,
      timestamp: "night",
      accommodationId: 102,
      userId: 202,
    },
    {
      id: 3,
      title: "Roommate Wanted for Spacious Condo",
      description: "Looking for a roommate to share a beautiful condo.",
      price: 800,
      timestamp: "month",
      accommodationId: 103,
      userId: 203,
    },
    {
      id: 4,
      title: "Charming Single Room for Rent",
      description: "Single room available in a lovely house.",
      price: 60,
      timestamp: "night",
      accommodationId: 104,
      userId: 204,
    },
    {
      id: 5,
      title: "Spacious House with Garden",
      description: "3-bedroom house with a beautiful garden and pool.",
      price: 2000,
      timestamp: "month",
      accommodationId: 105,
      userId: 205,
    },
  ];

}
