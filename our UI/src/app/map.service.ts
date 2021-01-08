import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {  mapDetails } from './pages/map/map';
import { items } from './layouts/admin-layout/items';
import { workers } from './pages/workers/workers';
import {   clock } from './components/sidebar/clock';
import { orders } from './pages/order/orders';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  constructor(private http : HttpClient) { }
  url : string = "http://localhost:8080/map/vertices";
  url2 : string = "http://localhost:8080/items";
  url3 : string = "http://localhost:8080/allWorkers";
  url4 : string = "http://localhost:8080/step";
  url5 : string = "http://localhost:8002/orders";


  getmap()
  {

   return this.http.get<mapDetails[]>(this.url);

  }

  getitems()
  {

   return this.http.get<items[]>(this.url2);

  }

  getworkers()
  {

   return this.http.get<workers[]>(this.url3);

  }
  getstep()
  {

   return this.http.get<clock[]>(this.url4);

  }

  getorders()
  {

   return this.http.get<orders[]>(this.url5);

  }

}
