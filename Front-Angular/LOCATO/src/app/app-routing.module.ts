import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostAdComponent } from './post-ad/post-ad/post-ad.component';
import { ListAdsComponent } from './components/list-ads/list-ads.component';
import { TypePostComponent } from './post-ad/type-post/type-post.component';
import { RentingAdComponent } from './post-ad/renting-ad/renting-ad.component';
import { RoommateAdComponent } from './post-ad/roommate-ad/roommate-ad.component';
import { AccomodationComponent } from './post-ad/accomodation/accomodation.component';

const routes: Routes = [
  { path: '', component: ListAdsComponent },
  { path: 'post-ad', component: PostAdComponent },
  { path: 'type-post', component: TypePostComponent },
  { path: 'renting-ad', component: RentingAdComponent },
  { path: 'roommate-ad', component: RoommateAdComponent },
  { path: 'accomodation', component:AccomodationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule,]
})
export class AppRoutingModule { }
