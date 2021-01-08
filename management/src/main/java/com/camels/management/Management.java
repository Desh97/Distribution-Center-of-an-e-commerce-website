package com.camels.management;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Management {
    OrderList orderlist = new OrderList();
    WorkerList workerList = new WorkerList();

    //updating orders & workers

    public void setNewOrder(JSONObject description) throws IOException, InterruptedException {
        int tempOrderNo = (int)description.get("orderNo");
        int tempMaxWeight = (int)description.get("maxWeight");
        HashMap<String,Integer> tempOrderItems = (HashMap<String,Integer>) description.get("orderItems");

        Order order = new Order();
        order.setOrder(tempOrderNo, tempMaxWeight, tempOrderItems);
        orderlist.addOrder(order);

        System.out.println(order.toString());

        matchTask();
    }

    public void setNewWorker(JSONObject description) throws IOException, InterruptedException {
        String tempWorkerName = (String)description.get("name");
        int tempWorkerCapacity = (int)description.get("capacity");

        Worker worker = new Worker();
        worker.setWorker(tempWorkerName, tempWorkerCapacity);
        workerList.addWorker(worker);

        System.out.println("Updating free worker "+worker.toString());

        matchTask();
    }

    public void matchTask() throws IOException, InterruptedException {
        Iterator<Order> ordItr = orderlist.getOrderList().iterator();
        Iterator<Worker> wrkItr = workerList.getWorkerList().iterator();

        boolean flag = Boolean.TRUE;
        while(wrkItr.hasNext() && flag){
            Worker tempWorker = wrkItr.next();
            while(ordItr.hasNext() && flag){
                Order tempOrder = ordItr.next();
                //System.out.println("inside iterator "+tempWorker.toString()+", "+tempOrder.toString());
                if(tempOrder.getMaxWeight() <= tempWorker.getCapacity()){
                    flag = Boolean.FALSE;
                    orderlist.getOrderList().remove(tempOrder);
                    workerList.getWorkerList().remove(tempWorker);
                    assignTask(tempWorker, tempOrder);
                    assignOrderStatus(String.valueOf(tempOrder.getOrderNo()));
                }
            }
        }
    }

    public void assignTask(Worker w, Order o) throws IOException, InterruptedException {

        Gson gson = new Gson();
        String orderDetails = gson.toJson(o);

        System.out.println("Assigning worker "+w.getName()+" to Order "+orderDetails);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, orderDetails);
        Request request = new Request.Builder()
                .url("http://localhost:8004/newOrder/"+w.getName())
                .method("PUT", body)
                .addHeader("Content-Type", "text/plain")
                .build();
        Response response = client.newCall(request).execute();
    }

    public void assignOrderStatus(String orderNo) throws IOException {
        //System.out.println("Updating OG..."+orderNo);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"status\": \"Processing\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8002/orders/"+orderNo+"/updateStatus")
                .method("PUT", body)
                .addHeader("Content-Type", "text/plain")
                .build();
        Response response = client.newCall(request).execute();
    }
}
