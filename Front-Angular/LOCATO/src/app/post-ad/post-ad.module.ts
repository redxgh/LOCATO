import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoommateAdComponent } from './roommate-ad/roommate-ad.component';
import { RentingAdComponent } from './renting-ad/renting-ad.component';



@NgModule({
  declarations: [
       RoommateAdComponent,
       RentingAdComponent
  ],
  imports: [
    CommonModule
  ]
})
export class PostAdModule { }
