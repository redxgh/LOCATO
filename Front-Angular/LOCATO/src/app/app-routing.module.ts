import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostAdComponent } from './post-ad/post-ad/post-ad.component';
import { ListAdsComponent } from './components/list-ads/list-ads.component';
import { TypePostComponent } from './post-ad/type-post/type-post.component';
import { RentingAdComponent } from './post-ad/renting-ad/renting-ad.component';
import { RoommateAdComponent } from './post-ad/roommate-ad/roommate-ad.component';
import { AccomodationComponent } from './post-ad/accomodation/accomodation.component';
import { PostImagesComponent } from './post-ad/post-images/post-images.component';
import { LocationComponent } from './post-ad/location/location.component';
import { AdDetailComponent } from './components/ad-detail/ad-detail.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { MylistAdsComponent } from './mylist-ads/mylist-ads.component';
import { AuthGuard } from './guards/auth.guard';
import { NotFoundComponent } from './not-found/not-found.component';

const routes: Routes = [
  { path: '', component: WelcomePageComponent},
  { path: 'list-ads', component: ListAdsComponent },
  { path: 'mylist-ads', component: MylistAdsComponent },

  { path: 'post-ad', component: PostAdComponent , canActivate : [AuthGuard], data : { roles : []}},
  { path: 'type-post', component: TypePostComponent },
  { path: 'renting-ad', component: RentingAdComponent },
  { path: 'roommate-ad', component: RoommateAdComponent },
  { path: 'accomodation', component:AccomodationComponent},
  { path: 'location', component:LocationComponent},
  { path: 'post-images', component:PostImagesComponent},
  { path: 'ad-detail/:id', component:AdDetailComponent},
  { path:"**",component:NotFoundComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule,]
})
export class AppRoutingModule { }
