import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostAdComponent } from './post-ad/post-ad/post-ad.component';
import { ListAdsComponent } from './components/list-ads/list-ads.component';

const routes: Routes = [
  { path: '' , component:ListAdsComponent},
  { path: 'post-ad', component: PostAdComponent, children:[
    // { path: '', redirectTo: 'profile', pathMatch: 'full' }, // Default route
    {path:'',component:ListAdsComponent},

  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule,]
})
export class AppRoutingModule { }
