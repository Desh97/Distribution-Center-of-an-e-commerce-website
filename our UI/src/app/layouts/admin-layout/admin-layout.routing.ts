import { Routes } from "@angular/router";

import { DashboardComponent } from "../../pages/dashboard/dashboard.component";

import { MapComponent } from "../../pages/map/map.component";


import { OrdersComponent } from "../../pages/order/orders.component";
import { WorkersComponent } from "../../pages/workers/workers.component";
import { NextActionComponent } from 'src/app/pages/workers/nextAction/nextAction.component';

// import { RtlComponent } from "../../pages/rtl/rtl.component";

export const AdminLayoutRoutes: Routes = [
  { path: "dashboard", component: DashboardComponent },
 
  { path: "maps", component: MapComponent },
  
  { path: "order", component:  OrdersComponent },
 
  { path: "workers", component:  WorkersComponent },
  { path: "nextAction", component: NextActionComponent },
  // { path: "rtl", component: RtlComponent }
];
