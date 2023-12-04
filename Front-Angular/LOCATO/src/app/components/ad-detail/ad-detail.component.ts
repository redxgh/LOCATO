import { Component, Input, inject } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Ad } from 'src/app/model/Ad';
import { AdService } from 'src/app/services/ad.service';

@Component({
  selector: 'app-ad-detail',
  templateUrl: './ad-detail.component.html',
  styleUrls: ['./ad-detail.component.scss']
})
export class AdDetailComponent {
  @Input()
  ad!: Ad;
  AdId: string;
  route: ActivatedRoute = inject(ActivatedRoute);




  constructor(private adService: AdService) {
    this.AdId = this.route.snapshot.params['id'];
    console.log('Ad id is :' + this.AdId);
  }
  ngOnInit(): void {
    this.findAdById();
  }

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

  extractFileName(filePath: string): string {
    const lastSlashIndex = Math.max(filePath.lastIndexOf('/'));
    if (lastSlashIndex !== -1) {
      console.log(filePath.substring(lastSlashIndex + 1))
      return filePath.substring(lastSlashIndex + 1);
    } else {
      return filePath;
    }
  }
}
