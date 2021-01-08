import { Component, OnInit } from "@angular/core";
import { items } from '../../layouts/admin-layout/items';
import { RestService } from 'src/app/rest.service';
import { OrderService } from 'src/app/order.service';
import { orders } from './orders';

@Component({
  selector: "app-tables",
  templateUrl: "orders.component.html"
})
export class OrdersComponent implements OnInit {
  public data: any;
  public clicked: boolean = true;
  public clicked1: boolean = false;
  public clicked2: boolean = false;
  public clicked3: boolean = false;
  _postsArray: orders[];
  
    constructor(private rs : OrderService) {
      setInterval(() => this.reloadPage(), 5000);
  }

  reloadPage() {
    
    this.ngOnInit();
}

columns = ["OrderNo","ItemList", "OrderStatus"];

index = ["orderNo", "orderItems", "orderStatus"];
orders : orders[] = [];
objToString (obj) {
  var str = '';
  for (var p in obj) {
    if (obj.hasOwnProperty(p)) {
      str += p + ':' + obj[p] + '\n';
    }
  }
  return str;
}



  ngOnInit(): void 
  {
    this.rs.getorders().subscribe
    (
      (response)=>
      {
        this.orders = response;
        
      },
      

      (error)=>
      {
        console.log("Error Occured :" +error);
      }

    )

    

    
    




    
  }
}