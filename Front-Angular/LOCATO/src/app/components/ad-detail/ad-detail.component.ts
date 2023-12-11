import { Component, Input, inject , ViewChild, ElementRef, AfterViewInit, OnInit} from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { Ad } from 'src/app/model/Ad';
import { AdService } from 'src/app/services/ad.service';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-ad-detail',
  templateUrl: './ad-detail.component.html',
  styleUrls: ['./ad-detail.component.scss']
})
export class AdDetailComponent implements AfterViewInit,OnInit{
  //for slider
  @ViewChild('items')
  items!: ElementRef;
  @ViewChild('nextItem')
  nextItem!: ElementRef;
  @ViewChild('previousItem')
  previousItem!: ElementRef;
  count = 0;
  itemCount!: number;

  //for ad
  @Input()
  ad!: Ad;
  AdId: string;
  route: ActivatedRoute = inject(ActivatedRoute);


  constructor(private adService: AdService,private http: HttpClient, private sanitizer: DomSanitizer) {
    this.AdId = this.route.snapshot.params['id'];
    console.log('Ad id is :' + this.AdId);
  }
  ngOnInit(): void {
    this.findAdById();
  }

  ngAfterViewInit() {
    this.itemCount = this.items.nativeElement.children.length;
    this.nextItem.nativeElement.addEventListener('click', this.showNextItem.bind(this));
    this.previousItem.nativeElement.addEventListener('click', this.showPreviousItem.bind(this));
   }

   showNextItem() {
    this.items.nativeElement.children[this.count].querySelector('img').classList.remove('active');

    if(this.count < this.itemCount - 1) {
      this.count++;
    } else {
      this.count = 0;
    }
    this.items.nativeElement.children[this.count].querySelector('img').classList.add('active');
    console.log(this.count);
   }

   showPreviousItem() {
    this.items.nativeElement.children[this.count].querySelector('img').classList.remove('active');

    if(this.count > 0) {
      this.count--;
    } else {
      this.count = this.itemCount - 1;
    }

    this.items.nativeElement.children[this.count].querySelector('img').classList.add('active');
    console.log(this.count);
   }

  //get ad
  findAdById(): void {
    this.adService.getAdById(this.AdId).subscribe(
      (ad: Ad) => {
        this.ad = ad;
        console.log('Found Ad: ', ad);
      },
      error => {
        console.error('Error fetching Ad: ', error);
      }
    );
  }

//get image
//testing
imagePathPrefix = 'A:/Integration project/LOCATO/Microservices/ad-service/src/main/resources/static/images/';
imageUrlPrefix = 'http://localhost:8081/images/';

getImage(imagePath: string): Observable<SafeUrl> {
 const imageName = imagePath.replace(this.imagePathPrefix, '');
 const imageUrl = `${this.imageUrlPrefix}${imageName.startsWith('/') ? imageName.substring(1) : imageName}`;
 return new Observable<SafeUrl>((observer) => {
   observer.next(imageUrl as SafeUrl);
   observer.complete();
 });
}




}
