import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { workers } from './pages/workers/workers';

@Injectable({
  providedIn: 'root'
})
export class WorkService {

  constructor(private http : HttpClient) { }

  url : string = "http://localhost:8080/allWorkers";

  getworkers()
  {

   return this.http.get<workers[]>(this.url);

  }
}
