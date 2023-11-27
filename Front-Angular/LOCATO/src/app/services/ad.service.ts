import { Injectable } from '@angular/core';
import { Ad } from '../model/Ad';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdService {
  private apiUrl = 'http://medtvherghvzel:8081/getAds';

  constructor(private http: HttpClient) { }

  getAds(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
