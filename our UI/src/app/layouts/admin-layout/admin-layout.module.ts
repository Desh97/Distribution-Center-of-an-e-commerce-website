import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";


import { AdminLayoutRoutes } from "./admin-layout.routing";
import { DashboardComponent } from "../../pages/dashboard/dashboard.component";

import { MapComponent } from "../../pages/map/map.component";

import { OrdersComponent } from "../../pages/order/orders.component";
import { WorkersComponent } from "../../pages/workers/workers.component";

// import { RtlComponent } from "../../pages/rtl/rtl.component";

import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { NextActionComponent } from 'src/app/pages/workers/nextAction/nextAction.component';



@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
  ],
  declarations: [
    DashboardComponent,
    OrdersComponent,
    WorkersComponent, 
    
    MapComponent,
    
    NextActionComponent
    // RtlComponent
  ]
})
export class AdminLayoutModule {}
