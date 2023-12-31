import { Component, Input, inject , ViewChild, ElementRef, AfterViewInit, OnInit, HostListener} from '@angular/core';
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
  private imgId = 1;


  constructor(private adService: AdService,private http: HttpClient, private sanitizer: DomSanitizer,private elRef: ElementRef) {
    this.AdId = this.route.snapshot.params['id'];
    console.log('Ad id is :' + this.AdId);
  }
  ngOnInit(): void {
    this.findAdById();
  }

  ngAfterViewInit() {
    const imgBtns = this.elRef.nativeElement.querySelectorAll('.img-select a');
    imgBtns.forEach((imgItem: HTMLElement, index: number) => {
      imgItem.addEventListener('click', (event) => {
        event.preventDefault();
        this.imgId = index + 1; // Adjust index to start from 1
        this.slideImage();
      });
    });
  }
  slideImage() {
    const displayWidth = this.elRef.nativeElement.querySelector('.img-showcase img:first-child').clientWidth;
    this.elRef.nativeElement.querySelector('.img-showcase').style.transform = `translateX(${- (this.imgId - 1) * displayWidth}px)`;
  }

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.slideImage();
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
imagePathPrefix = 'C:/Users/LENOVO/projetintegration/LOCATO/Microservices/ad-service/src/main/resources/static/images/';
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
