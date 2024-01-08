import { AfterViewInit, Component, ElementRef, Input, QueryList, ViewChildren } from '@angular/core';
import { Ad } from '../model/Ad';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-myad-card',
  templateUrl: './myad-card.component.html',
  styleUrls: ['./myad-card.component.scss']
})
export class MyadCardComponent implements AfterViewInit {
  @ViewChildren('cardImageGroup')
  cardImageGroups!: QueryList<ElementRef>;
  @ViewChildren('cardImageSwiperAfter')
  cardImageSwiperAfters!: QueryList<ElementRef>;
  @ViewChildren('cardImageSwiperBefore')
  cardImageSwiperBefores!: QueryList<ElementRef>;
  @ViewChildren('cardImageContainer')
  cardImageContainers!: QueryList<ElementRef>;
  @ViewChildren('cardImageIndicator')
  cardImageIndicators!: QueryList<ElementRef>;

  @Input() ad: Ad = {
    id: 0,
    title: "",
    description: "",
    price: 0,
    surface: 0,
    userId:"",
    accomodation: {
      images: [],
      rooms: 0,
      bathrooms: 0,
      type: '',
      category: '',
      location: '',
      best: 0,
    }
  }


  extractFileName(filePath: string): string {
    const lastSlashIndex = Math.max(filePath.lastIndexOf('/'));
    if (lastSlashIndex !== -1) {
      console.log(filePath.substring(lastSlashIndex + 1))
      return filePath.substring(lastSlashIndex + 1);
    } else {
      return filePath;
    }
  }
  imageLink = "";
ngOnInit(): void {
  this.imageLink = `http://127.0.0.1:8081/images/${this.ad.accomodation.images[0]}`;
  console.log(this.ad.accomodation.images[0])
}
//testing

 getImage(imagePath: string): Observable<SafeUrl> {
  const imageName = imagePath.replace(this.imageLink, '');
  const imageUrl = `${this.imageLink}${imageName.startsWith('/') ? imageName.substring(1) : imageName}`;
  return new Observable<SafeUrl>((observer) => {
    observer.next(imageUrl as SafeUrl);
    observer.complete();
  });
}

ngAfterViewInit() {

  this.cardImageGroups.forEach((cardImageGroup, index) => {
    const cardImageSwiperAfter = this.cardImageSwiperAfters.toArray()[index];
    const cardImageSwiperBefore = this.cardImageSwiperBefores.toArray()[index];
    const cardImageContainer = this.cardImageContainers.toArray()[index];
    const cardImageIndicators = Array.from(this.cardImageIndicators.toArray()[index].nativeElement.children) as HTMLElement[];

    let numberOfImages = cardImageGroup.nativeElement.childElementCount;
    let currentIndex = 0;
    let cardImageContainerWidth = cardImageContainer.nativeElement.clientWidth;

    const swiperHandle = (isBefore: boolean) => {
      if (isBefore) {
        if (currentIndex !== 0) {
          currentIndex--;
        }
      } else {
        if (currentIndex !== (numberOfImages - 1)) {
          currentIndex++;
        }
      }
      cardImageGroup.nativeElement.style.transform = `translate(-${currentIndex * cardImageContainerWidth}px)`;
      cardImageIndicators.forEach((item, itemIndex) => {
        if (itemIndex === currentIndex) {
          item.classList.add('card-image-indicator-active');
        } else {
          item.classList.remove('card-image-indicator-active');
        }
      });

      if (currentIndex === (numberOfImages - 1)) {
        cardImageSwiperAfter.nativeElement.classList.add('card-image-swiper-inactive');
      } else {
        cardImageSwiperAfter.nativeElement.classList.remove('card-image-swiper-inactive');
      }

      if (currentIndex === 0) {
        cardImageSwiperBefore.nativeElement.classList.add('card-image-swiper-inactive');
      } else {
        cardImageSwiperBefore.nativeElement.classList.remove('card-image-swiper-inactive');
      }
    };

    cardImageSwiperBefore.nativeElement.addEventListener('click', () => swiperHandle(true));
    cardImageSwiperAfter.nativeElement.addEventListener('click', () => swiperHandle(false));
  });
}
constructor(private router: Router) {}

editAd(adId: number) {
  this.router.navigate(['/edit-roommate-ad'], { queryParams: { adId: adId } });
}

}
