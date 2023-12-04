import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListAdsComponent } from './components/list-ads/list-ads.component';
import { AdCardComponent } from './components/ad-card/ad-card.component';
import { PostAdComponent } from './post-ad/post-ad/post-ad.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormServiceComponent } from './form-service/form-service.component';
import { AccomodationComponent } from './post-ad/accomodation/accomodation.component';
import { RentingAdComponent } from './post-ad/renting-ad/renting-ad.component';
import { RoommateAdComponent } from './post-ad/roommate-ad/roommate-ad.component';
import { TypePostComponent } from './post-ad/type-post/type-post.component';
import { PostImagesComponent } from './post-ad/post-images/post-images.component';
import { LocationComponent } from './post-ad/location/location.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { MapComponent } from './post-ad/map/map.component';
import { HttpClientModule } from '@angular/common/http';
import { AdDetailComponent } from './components/ad-detail/ad-detail.component';
@NgModule({
  declarations: [
    AppComponent,
    ListAdsComponent,
    AdCardComponent,
    PostAdComponent,
    AccomodationComponent,
    PostAdComponent,
    RentingAdComponent,
    RoommateAdComponent,
    TypePostComponent,
    NavbarComponent,
    FormServiceComponent,
    PostImagesComponent,
    LocationComponent,
    MapComponent,
    AdDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    LeafletModule,
    HttpClientModule,


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
