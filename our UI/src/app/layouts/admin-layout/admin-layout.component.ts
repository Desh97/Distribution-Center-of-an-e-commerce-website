import { Component, OnInit } from "@angular/core";
import { RestService } from 'src/app/rest.service';
import { items } from 'src/app/layouts/admin-layout/items';

@Component({
  selector: "app-admin-layout",
  templateUrl: "./admin-layout.component.html",
  styleUrls: ["./admin-layout.component.scss"]
})
export class AdminLayoutComponent implements OnInit {
  public sidebarColor: string = "red";

  constructor(private rs : RestService) {}

  columns = ["ID","Name", "Suppliers","Weight","Location"];

index = ["id", "name", "supplier","weight","location"];

items : items[] = [];
  changeSidebarColor(color){
    var sidebar = document.getElementsByClassName('sidebar')[0];
    var mainPanel = document.getElementsByClassName('main-panel')[0];

    this.sidebarColor = color;

    if(sidebar != undefined){
        sidebar.setAttribute('data',color);
    }
    if(mainPanel != undefined){
        mainPanel.setAttribute('data',color);
    }
  }
  changeDashboardColor(color){
    var body = document.getElementsByTagName('body')[0];
    if (body && color === 'white-content') {
        body.classList.add(color);
    }
    else if(body.classList.contains('white-content')) {
      body.classList.remove('white-content');
    }
  }
  ngOnInit() {
    this.rs.getitems().subscribe
    (
      (response)=>
      {
        this.items = response;
        
      },

      (error)=>
      {
        console.log("Error Occured :" +error);
      }

    )
  }
}
