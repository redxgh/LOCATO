import { Component, AfterViewInit } from '@angular/core';
import { Ad } from 'src/app/model/Ad';
import * as $ from 'jquery';
import * as Aos from 'aos';
import { AdService } from 'src/app/ad.service';

@Component({
  selector: 'app-list-ads',
  templateUrl: './list-ads.component.html',
  styleUrls: ['./list-ads.component.scss'],
})
export class ListAdsComponent implements AfterViewInit {

  ads:Ad[];
  constructor(private adService : AdService){
    this.ads=adService.listAds();
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
