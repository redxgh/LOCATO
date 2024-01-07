import { Component, AfterViewInit, OnDestroy } from '@angular/core';
import { Ad } from 'src/app/model/Ad';
import * as Aos from 'aos';
import { Subscription } from 'rxjs';
import { AdService } from 'src/app/services/ad.service';
import { SecurityService } from 'src/app/services/security.service';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-mylist-ads',
  templateUrl: './mylist-ads.component.html',
  styleUrls: ['./mylist-ads.component.scss']
})
export class MylistAdsComponent implements AfterViewInit, OnDestroy {
  ads: Ad[] = [];
  filteredAdList: Ad[] = [];
  searchText: string = '';
  adsSubscription: Subscription | undefined;
  private agentProfile: any = {};

  constructor(
    private adService: AdService,
    private secService: SecurityService,
    private kc: KeycloakService
  ) {}

  async ngOnInit() {
    await this.loadUserProfile();
    this.fetchAds();
  }

  ngOnDestroy(): void {
    this.unsubscribeAds();
  }

  async ngAfterViewInit() {
    Aos.init();
  }

  async loadUserProfile() {
    this.agentProfile = await this.kc.loadUserProfile();
    console.log(this.agentProfile.id);
  }

  fetchAds() {
    const userId = this.agentProfile.id;
    if (userId) {
      this.adsSubscription = this.adService.getAdsByUserId(userId).subscribe({
        next: (ads) => {
          this.ads = ads;
          this.filteredAdList = this.ads;
          console.log(ads);
        },
        error: (error) => {
          console.error('Error fetching ads:', error);
        }
      });
    }
  }

  unsubscribeAds() {
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
}

