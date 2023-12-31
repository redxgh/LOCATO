import { Component, AfterViewInit,ElementRef, ViewChildren, QueryList  } from '@angular/core';
import { Ad } from 'src/app/model/Ad';
import * as $ from 'jquery';
import * as Aos from 'aos';
import { AdService } from 'src/app/services/ad.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-list-ads',
  templateUrl: './list-ads.component.html',
  styleUrls: ['./list-ads.component.scss'],
})
export class ListAdsComponent implements AfterViewInit {
  ads: Ad[] = [];
  filteredAdList: Ad[] = [];
  searchText: string = '';
  adsSubscription: Subscription | undefined;


  constructor(private adService: AdService) {
    this.adsSubscription = this.adService.getAds().subscribe({
      next: (ads) => {
        this.ads = ads;
        this.filteredAdList = this.ads;
        console.log(ads)
      },
      error: (error) => {
        console.error('Error fetching ads:', error);
      }
    });
  }

 ngOnDestroy(): void {
    if (this.adsSubscription) {
      this.adsSubscription.unsubscribe();
    }
  }

  filterResults() {
    if (!this.searchText) {
      this.filteredAdList = this.ads;
    } else {
      this.filteredAdList = this.ads.filter(ad => {
        return (
          ad?.title.toLowerCase().includes(this.searchText.toLowerCase()) ||
          this.containsImage(ad, this.searchText.toLowerCase())
        );
      });
    }
  }

  private containsImage(ad: Ad, searchText: string): boolean {
    return ad?.accomodation.images?.some(imageUrl => imageUrl.toLowerCase().includes(searchText));
  }


  ngAfterViewInit() {
    Aos.init();
  }

  }

