import { Component, OnInit } from "@angular/core";



@Component({
  selector: "app-tables",
  templateUrl: "nextAction.component.html"
})
export class NextActionComponent implements OnInit {

//userData : any=[
 //   {name:"sa",location:"A41",capacity:"45"}

 // ]
 /* constructor(private rs : WorkService) {}

columns = ["Name", "Location", "Capacity","Action"];

index = ["name", "location", "capacity"];

workers : workers[] = [];

  ngOnInit(): void 
  {
    this.rs.getworkers().subscribe
    (
      (response)=>
      {
        this.workers = response;
      },

      (error)=>
      {
        console.log("Error Occured :" +error);
      }

    )
  }*/
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
  
constructor(){}
ngOnInit(){}
}
