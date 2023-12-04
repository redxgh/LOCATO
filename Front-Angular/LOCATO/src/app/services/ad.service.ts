import { Injectable } from '@angular/core';
import { Ad } from '../model/Ad';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdService {
  private apiUrl = 'http://localhost:8081/getAds';

  constructor(private http: HttpClient) { }

  getAds(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getAdById(adId: string): Observable<any> {
    const adUrl = `http://localhost:8081/getAdById/${adId}`;
    return this.http.get<any>(adUrl);
  }

}
