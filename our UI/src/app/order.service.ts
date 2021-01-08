import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { orders } from './pages/order/orders';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http : HttpClient) { }

  url : string = "http://localhost:8002/orders";

  getorders()
  {

   return this.http.get<orders[]>(this.url);

  }
}
