import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormServiceService {
  public title: string | undefined;
  price: number | undefined;
  gender: number | undefined;
  description: string | undefined;

  constructor() { }
}
