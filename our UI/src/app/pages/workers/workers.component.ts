import { Component, OnInit } from "@angular/core";
import { workers } from '../workers/workers';
import { WorkService } from 'src/app/work.service';
import { Observable, interval, Subscription } from 'rxjs';

@Component({
  selector: "app-tables",
  templateUrl: "workers.component.html"
})
export class WorkersComponent implements OnInit {
  

//userData : any=[
 //   {name:"sa",location:"A41",capacity:"45"}

 // ]
  constructor(private rs : WorkService) {

    setInterval(() => this.reloadPage(), 5000);
  }

  reloadPage() {
    
    this.ngOnInit();
}

columns = ["Name", "Location", "Capacity","Weight","HoldingItems","PreviousAction"];

index = ["name", "location", "capacity","weight","holdingItems","actionHistory"];

workersArray : workers[] = [];

objToString (obj) {
  //console.log("inside function")
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

    
      
    this.rs.getworkers().subscribe
    (
      (response)=>
      {
        this.workersArray = response;
        //console.log(this.objToString(this.workersArray))
       
      },

      (error)=>
      {
        console.log("Error Occured :" +error);
      }
      

    )
  }
  
 /* workers: workers[] = [ ];

  constructor(private http: HttpClient) { }

  ngOnInit() {

    this.getAllWorkers();

  }

  public getAllWorkers() {

      let url = "http://localhost:8080/configuration"
      this.http.get<workers[]>(url).subscribe(
        res => {
          this.workers = res;
        },
        error => {
          alert("An error has occuerd")
        }
      );
  }*/
  /*private page:number=0;
  private workers:Array<any>;
  private pages:Array<number>;
  constructor(private _myservice:RestService){

  }

  ngOnInit(){
    this.getWorkers();
  }
  getWorkers(){
    this._myservice.getworkers(this.page).subscribe(
      data=>{
        //console.log(data);
        this.workers = data['content'];
        this.pages = new Array(data['totalPages'])
      },
      (error)=>{
        console.log(error.error.message);
      }
    )
  }*/
  

}
