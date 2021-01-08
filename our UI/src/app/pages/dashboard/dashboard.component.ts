import { Component, OnInit } from "@angular/core";
import { workers } from '../workers/workers';
import { WorkService } from 'src/app/work.service';
import { MapService } from 'src/app/map.service';
import { orders } from '../order/orders';


@Component({
  selector: "app-dashboard",
  templateUrl: "dashboard.component.html",
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  

  name = 'Camels';

  i =0;
  section1 = [ ];
  section2 = [ ];
  section3 = [ ];
  section4 = [ ];
  section5 = [ ];
  section6 = [ ];

  constructor(private map : MapService){
    setInterval(() => this.reloadPage(), 5000);
  }
  reloadPage() {
    
    this.ngOnInit();
}
  

  columns = ["Name", "Location", "HoldingItems"];

  index = ["name", "location", "holdingItems"];
  columnss = ["OrderNo","ItemList", "OrderStatus"];

  indexs = ["orderNo", "orderItems", "orderStatus"];
  
//index = ["name", "location", "capacity"];

maps  = [];
items  = [];
workers = [];
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
//maps = ["a1.0","a1.1","a1.2","a1.3"];


  ngOnInit(): void
  {
    this.map.getmap().subscribe
    (
      (response)=>
      {
        this.maps = response;
        this.section1 = [ ];
      this.section2 = [ ];
      this.section3 = [ ];
      this.section4 = [ ];
      this.section5 = [ ];
      this.section6 = [ ];
        for (let x1 = 0;x1<this.maps.length ; x1++){
          const obj = {name:this.maps[x1],shelfA:"",shelfB:"",shelfC:"",shelfASup:"",shelfBSup:"",shelfCSup:"",workerList:[{workerName:""},{workerName:""}]};
          const char = obj.name.charAt(1);
          switch(char) {
            case '1': {
              this.section1.push(obj);
              break;
            }
            case '2': {
              this.section2.push(obj);
              break;
            }
            case '3': {
              this.section3.push(obj);
              break;
            }
            case '4': {
              this.section4.push(obj);
              break;
            }
            case '5': {
              this.section5.push(obj);
              break;
            }
            case '6': {
              this.section6.push(obj);
              break;
            }
            default: {
                break;
            }
          }
        }
      },

      (error)=>
      {
        console.log("Error Occured :" +error);
      }


    )


    this.map.getitems().subscribe
    (
      (response)=>
      {
        this.items = response;
        for (let x2 = 0;x2<this.items.length ; x2++) {
          const obj = {itemName: this.items[x2].name, location: this.items[x2].location, supplier: this.items[x2].supplier};
          const char = obj.location.charAt(1);

          switch(char) {
            case '1': {
              for (let s1 = 0;s1<this.section1.length ; s1++) {
                if(this.section1[s1].name == obj.location.substring(0,4)){
                  const shelfLetter = obj.location.charAt(5);
                  switch(shelfLetter) {
                    case 'A': {
                      this.section1[s1].shelfA = "Shelf A: "+obj.itemName
                      this.section1[s1].shelfASup = "("+obj.supplier+")";
                      break;
                    }
                    case 'B': {
                      this.section1[s1].shelfB = "Shelf B: "+obj.itemName
                      this.section1[s1].shelfBSup = "("+obj.supplier+")";
                      break;
                    }
                    case 'C': {
                      this.section1[s1].shelfC = "Shelf C: "+obj.itemName
                      this.section1[s1].shelfCSup = "("+obj.supplier+")";
                      break;
                    }
                    default: {
                      break;
                    }
                  }
                  console.log("Section 1 complete "+this.section1[s1].name+this.section1[s1].shelfA+this.section1[s1].shelfASup+this.section1[s1].shelfB+this.section1[s1].shelfBSup);
                }
              }
              break;
            }
            case '2': {
              for (let s2 = 0;s2<this.section2.length ; s2++) {
                if(this.section2[s2].name == obj.location.substring(0,4)){
                  const shelfLetter = obj.location.charAt(5);
                  switch(shelfLetter) {
                    case 'A': {
                      this.section2[s2].shelfA = "Shelf A: "+obj.itemName
                      this.section2[s2].shelfASup = "("+obj.supplier+")";
                      break;
                    }
                    case 'B': {
                      this.section2[s2].shelfB = "Shelf B: "+obj.itemName
                      this.section2[s2].shelfBSup = "("+obj.supplier+")";
                      break;
                    }
                    case 'C': {
                      this.section2[s2].shelfC = "Shelf C: "+obj.itemName
                      this.section2[s2].shelfCSup = "("+obj.supplier+")";
                      break;
                    }
                    default: {
                      break;
                    }
                  }
                  console.log("Section 2 complete "+this.section2[s2].name+this.section2[s2].shelfA+this.section2[s2].shelfASup+this.section2[s2].shelfB+this.section2[s2].shelfBSup);
                }
              }
              break;
            }
            case '3': {
              for (let s3 = 0;s3<this.section3.length ; s3++) {
                if(this.section3[s3].name == obj.location.substring(0,4)){
                  const shelfLetter = obj.location.charAt(5);
                  switch(shelfLetter) {
                    case 'A': {
                      this.section3[s3].shelfA = "Shelf A: "+obj.itemName
                      this.section3[s3].shelfASup = "("+obj.supplier+")";
                      break;
                    }
                    case 'B': {
                      this.section3[s3].shelfB = "Shelf B: "+obj.itemName
                      this.section3[s3].shelfBSup = "("+obj.supplier+")";
                      break;
                    }
                    case 'C': {
                      this.section3[s3].shelfC = "Shelf C: "+obj.itemName
                      this.section3[s3].shelfCSup = "("+obj.supplier+")";
                      break;
                    }
                    default: {
                      break;
                    }
                  }
                  console.log("Section 3 complete "+this.section3[s3].name+this.section3[s3].shelfA+this.section3[s3].shelfASup+this.section3[s3].shelfB+this.section3[s3].shelfBSup);
                }
              }
              break;
            }
            case '4': {
              for (let s4 = 0;s4<this.section4.length ; s4++) {
                if(this.section4[s4].name == obj.location.substring(0,4)){
                  const shelfLetter = obj.location.charAt(5);
                  switch(shelfLetter) {
                    case 'A': {
                      this.section4[s4].shelfA = "Shelf A: "+obj.itemName
                      this.section4[s4].shelfASup = "("+obj.supplier+")";
                      break;
                    }
                    case 'B': {
                      this.section4[s4].shelfB = "Shelf B: "+obj.itemName
                      this.section4[s4].shelfBSup = "("+obj.supplier+")";
                      break;
                    }
                    case 'C': {
                      this.section4[s4].shelfC = "Shelf C: "+obj.itemName
                      this.section4[s4].shelfCSup = "("+obj.supplier+")";
                      break;
                    }
                    default: {
                      break;
                    }
                  }
                  console.log("Section 4 complete "+this.section4[s4].name+this.section4[s4].shelfA+this.section4[s4].shelfASup+this.section4[s4].shelfB+this.section4[s4].shelfBSup);
                }
              }
              break;
            }
            case '5': {
              for (let s5 = 0;s5<this.section5.length ; s5++) {
                if(this.section5[s5].name == obj.location.substring(0,4)){
                  const shelfLetter = obj.location.charAt(5);
                  switch(shelfLetter) {
                    case 'A': {
                      this.section5[s5].shelfA = "Shelf A: "+obj.itemName
                      this.section5[s5].shelfASup = "("+obj.supplier+")";
                      break;
                    }
                    case 'B': {
                      this.section5[s5].shelfB = "Shelf B: "+obj.itemName
                      this.section5[s5].shelfBSup = "("+obj.supplier+")";
                      break;
                    }
                    case 'C': {
                      this.section5[s5].shelfC = "Shelf C: "+obj.itemName
                      this.section5[s5].shelfCSup = "("+obj.supplier+")";
                      break;
                    }
                    default: {
                      break;
                    }
                  }
                  console.log("Section 5 complete "+this.section5[s5].name+this.section5[s5].shelfA+this.section5[s5].shelfASup+this.section5[s5].shelfB+this.section5[s5].shelfBSup);
                }
              }
              break;
            }
            case '6': {
              for (let s6 = 0;s6<this.section6.length ; s6++) {
                if(this.section6[s6].name == obj.location.substring(0,4)){
                  const shelfLetter = obj.location.charAt(5);
                  switch(shelfLetter) {
                    case 'A': {
                      this.section6[s6].shelfA = "Shelf A: "+obj.itemName
                      this.section6[s6].shelfASup = "("+obj.supplier+")";
                      break;
                    }
                    case 'B': {
                      this.section6[s6].shelfB = "Shelf B: "+obj.itemName
                      this.section6[s6].shelfBSup = "("+obj.supplier+")";
                      break;
                    }
                    case 'C': {
                      this.section6[s6].shelfC = "Shelf C: "+obj.itemName
                      this.section6[s6].shelfCSup = "("+obj.supplier+")";
                      break;
                    }
                    default: {
                      break;
                    }
                  }
                  console.log("Section 6 complete "+this.section6[s6].name+this.section6[s6].shelfA+this.section6[s6].shelfASup+this.section6[s6].shelfB+this.section6[s6].shelfBSup);
                }
              }
              break;
            }
            default: {
              break;
            }
          }
          console.log("Looping Items..."+obj.itemName+" "+obj.location.substring(0,4));
        }
      },

      (error)=>
      {
        console.log("Error Occured :" +error);
      }

    )

    this.map.getworkers().subscribe
    (
      (response)=>
      {
        this.workers = response;
        for (let x3 = 0;x3<this.workers.length ; x3++){
          const obj = {workerName: this.workers[x3].name, location: this.workers[x3].location};
          console.log("Worker accepted :" +obj.workerName+" "+obj.location);
          const char = obj.location.charAt(1);
          switch(char) {
            case '1': {
              for (let s1 = 0;s1<this.section1.length ; s1++) {
                if(this.section1[s1].name == obj.location){
                  if(this.section1[s1].workerList[0].workerName == ""){
                    this.section1[s1].workerList[0].workerName = obj.workerName;
                  }
                  else{
                    this.section1[s1].workerList[1].workerName = obj.workerName;
                  }
                }
              }
              break;
            }
            case '2': {
              for (let s2 = 0;s2<this.section2.length ; s2++) {
                if(this.section2[s2].name == obj.location){
                  if(this.section2[s2].workerList[0].workerName == ""){
                    this.section2[s2].workerList[0].workerName = obj.workerName;
                  }
                  else{
                    this.section2[s2].workerList[1].workerName = obj.workerName;
                  }
                }
              }
              break;
            }
            case '3': {
            for (let s3 = 0;s3<this.section3.length ; s3++) {
              if(this.section3[s3].name == obj.location){
                if(this.section3[s3].workerList[0].workerName == ""){
                  this.section3[s3].workerList[0].workerName = obj.workerName;
                }
                else{
                  this.section3[s3].workerList[1].workerName = obj.workerName;
                }
              }
            }
              break;
            }
            case '4': {
            for (let s4 = 0;s4<this.section4.length ; s4++) {
              if(this.section4[s4].name == obj.location){
                if(this.section4[s4].workerList[0].workerName == ""){
                  this.section4[s4].workerList[0].workerName = obj.workerName;
                }
                else{
                  this.section4[s4].workerList[1].workerName = obj.workerName;
                }
              }
            }
              break;
            }
            case '5': {
            for (let s5 = 0;s5<this.section5.length ; s5++) {
              if(this.section5[s5].name == obj.location){
                if(this.section5[s5].workerList[0].workerName == ""){
                  this.section5[s5].workerList[0].workerName = obj.workerName;
                }
                else{
                  this.section5[s5].workerList[1].workerName = obj.workerName;
                }
              }
            }
              break;
            }
            case '6': {
            for (let s6 = 0;s6<this.section6.length ; s6++) {
              if(this.section6[s6].name == obj.location){
                if(this.section6[s6].workerList[0].workerName == ""){
                  this.section6[s6].workerList[0].workerName = obj.workerName;
                }
                else{
                  this.section6[s6].workerList[1].workerName = obj.workerName;
                }
              }
            }
              break;
            }
            default: {
              break;
            }
          }
        }
        console.log(this.section1[0].workerList[0].workerName)
      },

      (error)=>
      {
        console.log("Error Occured :" +error);
      }


    )
    this.map.getorders().subscribe
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