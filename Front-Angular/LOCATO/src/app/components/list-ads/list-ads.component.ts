import { Component, AfterViewInit } from '@angular/core';
import { Ad } from 'src/app/model/Ad';
import * as $ from 'jquery';
import * as Aos from 'aos';

@Component({
  selector: 'app-list-ads',
  templateUrl: './list-ads.component.html',
  styleUrls: ['./list-ads.component.scss'],
})
export class ListAdsComponent implements AfterViewInit {
  ads: Ad[] = [
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
  ngAfterViewInit() {
    //sliders 
    $('.slider').each(function () {
      var slideCount = $(this).find('ul li').length;
      var slideWidth = $(this).find('ul li').first().width() || 0;
      var slideHeight = $(this).find('ul li').height();
      var sliderUlWidth = slideCount * slideWidth;

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
