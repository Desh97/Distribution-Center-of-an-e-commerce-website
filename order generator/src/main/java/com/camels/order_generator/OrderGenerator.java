package com.camels.order_generator;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OrderGenerator {

    RegisteredServicesList ServicesList = new RegisteredServicesList();

    int orderCount = 100;
    ItemList availableItems = new ItemList();
    OrderList generatedOrders = new OrderList();
    boolean ManagementServiceFlag = Boolean.FALSE;

    public OrderGenerator() throws JSONException, IOException {
        setupItems();
    }

    //Setting Up Available Items
    public void setupItems() throws JSONException, IOException {

        JSONArray newArray = new JSONArray(getSimulatorItems());
        for(int i=0; i<newArray.length();i++){
            JSONObject tempObj = (JSONObject) newArray.get(i);
            String tempName = (String)tempObj.get("id");
            int tempWeight = (int)tempObj.get("weight");
            Item tempItem = new Item();
            tempItem.setItem(tempName, tempWeight);
            availableItems.addItem(tempItem);
        }
        System.out.println("Item list created: "+ availableItems.getItemList());
    }

    public String getSimulatorItems() throws JSONException, IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/items")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

            String body = response.body().string();
            return body;

    }

    //Calculating probabilities

    public boolean orderProbability(){
        Random rand = new Random();
        int randInt = rand.nextInt(100);
        if(randInt<20){
            return true;
        }
        else{
            return false;
        }
    }

    public int itemProbability(){
        Random rand = new Random();
        int randInt = rand.nextInt(100);
        if(randInt<50){return 1;}
        else if(randInt<75){return 2;}
        else if(randInt<88){return 3;}
        else if(randInt<94){return 4;}
        else {return 5;}
    }

    public int itemQtyProbability(){
        Random rand = new Random();
        int randInt = rand.nextInt(100);
        if(randInt<67){return 1;}
        else if(randInt<97){return 2;}
        else {return 3;}
    }

    public int RandomItemId(int max, Boolean[] usedIds){
        Random rand = new Random();
        int randInt = rand.nextInt(max);
        if(Arrays.asList(usedIds).contains(false)){
            if(usedIds[randInt]==Boolean.TRUE){
                return RandomItemId(max, usedIds);
            }
            else{
                usedIds[randInt] = Boolean.TRUE;
                return randInt;
            }
        }
        return randInt;
    }

    //Generating Order

    public void newOrder() throws IOException, JSONException {
        if (orderProbability()) {
                generateOrder();
            }
    }

    public void generateOrder() throws IOException, JSONException {
        Order newOrder = new Order();
        newOrder.setOrderNo(orderCount++);
        newOrder.setOrderStatus("NEW");

        Boolean[] usedItemIds = new Boolean[availableItems.getSize()];
        Arrays.fill(usedItemIds, Boolean.FALSE);

        for(int i=0; i<itemProbability();i++){
            Item tempItem = availableItems.getItem(RandomItemId(availableItems.getSize(),usedItemIds));
            newOrder.setOrderItem(tempItem, itemQtyProbability());

            if(newOrder.getMaxWeight()<tempItem.getWeight()){
                newOrder.setMaxWeight(tempItem.getWeight());
            }
        }
        System.out.println(newOrder.toString());

        if(ManagementServiceFlag){
            generatedOrders.addOrder(newOrder);
            pushOrder(newOrder);
        }


    }



    //Endpoint Functions

    public String getOrders(){
        Gson gson = new Gson();
        String orders = gson.toJson(generatedOrders.getOrderList());
        System.out.println("Sending order list: "+orders);
        return orders;
    }

    public String getOrderDetails(int id) throws IOException {
        return generatedOrders.getOrderById(id).toString();

    }


    public void RegisterService(String des) throws JSONException, InterruptedException {
        JSONObject tempService = new JSONObject(des);
        String name = (String)tempService.get("name");
        String uri = (String)tempService.get("uri");
        RegisteredServices newService = new RegisteredServices(name, uri);
        ServicesList.addService(newService);
        ManagementServiceFlag = Boolean.TRUE;
        System.out.println("Service Registered");
    }

    private void pushOrder(Order newOrder) throws IOException, JSONException {
        for(int i=0;i<ServicesList.getListSize();i++){
            RegisteredServices tempService =  ServicesList.getServicesByID(i);
            String tempUri = tempService.getUri();
            sendMessage(tempUri, newOrder);
        }
    }

    private void sendMessage(String tempUri, Order orderDetails) throws IOException, JSONException {

        Gson gson = new Gson();
        String jsonMessage = gson.toJson(orderDetails);

        //System.out.println("Passing message: "+jsonMessage);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonMessage);
        Request request = new Request.Builder()
                .url(tempUri)
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }


    public void setOrderStatus(int id, String description) throws JSONException {
        JSONObject tempService = new JSONObject(description);
        String status = (String)tempService.get("status");
        Order o = generatedOrders.getOrderById(id);
        o.setOrderStatus(status);
        System.out.println("Order Status updated... "+o.toString());
    }

    public void clockSync() throws JSONException, IOException {
        newOrder();
    }

}
