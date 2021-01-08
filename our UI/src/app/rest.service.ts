import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { items } from './layouts/admin-layout/items';

@Injectable({
  providedIn: 'root'
})
export class RestService {
  
  constructor(private http : HttpClient) { }

  url : string = "http://localhost:8080/items";

  getitems()
  {

   return this.http.get<items[]>(this.url);

  }
}
