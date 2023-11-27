import { Component, AfterViewInit } from '@angular/core';
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
    //sliders
    $('.slider').each(function () {
      var slideCount = $(this).find('ul li').length;
      var slideWidth = $(this).find('ul li').first().width() || 0;
      var slideHeight = $(this).find('ul li').height();
      var sliderUlWidth = slideCount * slideWidth;

      // idha ma3ana hata image bch y'affichi just el tawira loula
      if (slideCount === 1) {
        $(this).find('ul li').show(); // show the single image
        $(this).find('.control_next').hide();
        $(this).find('.control_prev').hide();
        return;
      }
      $(this).find('ul').css({ width: sliderUlWidth, marginLeft: -slideWidth });

      $(this).find('ul li:last-child').prependTo($(this).find('ul'));
      function moveLeft(slider: JQuery<any>) {
        var sliderUl = $(slider).find('ul');
        sliderUl.animate(
          {
            left: +slideWidth,
          },
          200,
          function () {
            sliderUl.find('li:last-child').prependTo(sliderUl);
            sliderUl.css('left', '');
          }
        );
      }

      function moveRight(slider: JQuery<any>) {
        var sliderUl = $(slider).find('ul');
        sliderUl.animate(
          {
            left: -slideWidth,
          },
          200,
          function () {
            sliderUl.find('li:first-child').appendTo(sliderUl);
            sliderUl.css('left', '');
          }
        );
      }

      $(this).on('click', 'a.control_prev', function () {
        var slider = $(this).closest('.slider');
        moveLeft(slider);
      });

      $(this).on('click', 'a.control_next', function () {
        var slider = $(this).closest('.slider');
        moveRight(slider);
      });
    });
    //Aos(animate on scroll animation) animation
    Aos.init();
  }
}
