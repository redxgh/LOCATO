import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListAdsComponent } from './components/list-ads/list-ads.component';
import { AdCardComponent } from './components/ad-card/ad-card.component';
import { PostAdComponent } from './post-ad/post-ad/post-ad.component';
import { NavbarComponent } from './navbar/navbar.component';
@NgModule({
  declarations: [
    AppComponent,
    ListAdsComponent,
    AdCardComponent,
    PostAdComponent,
    NavbarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
