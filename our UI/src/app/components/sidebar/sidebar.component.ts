import { Component, OnInit } from "@angular/core";
import { MapService } from 'src/app/map.service';
import {   clock } from './clock';

declare interface RouteInfo {
  path: string;
  title: string;
  
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  {
    path: "/dashboard",
    title: "Dashboard",
    
    icon: "icon-chart-pie-36",
    class: ""
  },
  
  
  {
    path: "/maps",
    title: "Maps",
    
    icon: "icon-pin",
    class: "" },
  
  {
    path: "/order",
    title: "Order List",
    icon: "tim-icons icon-bag-16",
    
    class: ""
  },
  
  {
    path: "/workers",
    title: "Worker List",
    icon: "tim-icons icon-single-02",
    
    
    class: ""
  },
  
];

@Component({
  selector: "app-sidebar",
  templateUrl: "./sidebar.component.html",
  styleUrls: ["./sidebar.component.css"]
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor(private clock : MapService) {
    setInterval(() => this.reloadPage(), 5000);
  }
  reloadPage() {
    
    this.ngOnInit();
}
  columns = ["Step"];

  index = ["step"];
  
  temp = [];

  stepsObj :object;
  
  newStep =[];
  objToString (obj) {
    var str = '';
    for (var p in obj) {
      if (obj.hasOwnProperty(p)) {
        str += p + ':' + obj[p] + '\n';
      }
    }
    //console.log("inside function")
    return str;
  }
  ngOnInit(){
    
    this.clock.getstep().subscribe
    (
      (response)=>
      {
        this.stepsObj = response;
        
        console.log(this.objToString(this.stepsObj))

        this.temp = [this.objToString(this.stepsObj)];

      },

      (error)=>
      {
        console.log("Error Occured :" +error);
      }
      

    )
    this.menuItems = ROUTES.filter(menuItem => menuItem);


  }
  isMobileMenu() {
    if (window.innerWidth > 991) {
      return false;
    }
    return true;
  }
}
