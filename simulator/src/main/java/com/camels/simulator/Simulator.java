package com.camels.simulator;

import com.google.gson.Gson;
import org.jgrapht.graph.DefaultEdge;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

public class Simulator {

    int step = 0;
    Map map = new Map();
    ArrayList<Item> itemArray = new ArrayList<Item>();
    ArrayList<Worker> workerArray = new ArrayList<Worker>();

    //Configuration functions

    public void setConfiguration(JSONObject des){
        System.out.println("Setting map...");
        setMap(des);
        System.out.println("Setting workers...");
        setWorkers(des);
        System.out.println("Setting items...");
        setItems(des);
    }

    public void setMap(JSONObject des){
        int aisles = (int)des.get("aisles");
        int sections = (int)des.get("sections");
        int shelves = (int)des.get("shelves");
        ArrayList packagingAreas = (ArrayList)des.get("packagingAreas");

        map.setMap(aisles, sections, shelves, packagingAreas);
    }

    public void setWorkers(JSONObject des){

        ArrayList workersArray = (ArrayList)des.get("workers");
        JSONArray workers = new JSONArray(workersArray);

        for (int i=0; i < workers.length(); i++) {
            org.json.JSONObject o = workers.getJSONObject(i);
            Gson gson = new Gson();
            Worker temp = new Worker();
            temp = gson.fromJson(String.valueOf(o),Worker.class);
            workerArray.add(temp);
        }
        System.out.println("Worker Array Created: "+workerArray.toString());
    }

    public void setItems(JSONObject des){

        ArrayList itemsArray = (ArrayList)des.get("items");
        JSONArray items = new JSONArray(itemsArray);

        for (int i=0; i < items.length(); i++) {
            org.json.JSONObject o = items.getJSONObject(i);
            Gson gson = new Gson();
            Item temp = gson.fromJson(String.valueOf(o),Item.class);
            itemArray.add(temp);
            setItemLocations(temp);
        }
        System.out.println("Item Array Created: "+itemArray.toString());
    }

    private void setItemLocations(Item tempItem) {
        ArrayList<Vertex> vertices = map.getVerticesArray();
        Boolean flag = Boolean.TRUE;
        String shelfLetter = "0";
        for(int s=1;s<=map.getShelves() && flag;s++){
            switch(s){
                case 1: shelfLetter = "A"; break;
                case 2: shelfLetter = "B"; break;
                case 3: shelfLetter = "C"; break;
                case 4: shelfLetter = "D"; break;
                case 5: shelfLetter = "E"; break;
            }
            for(int i=0;i<map.getVerticesArray().size() && flag;i++){
                Vertex tempVertex = vertices.get(i);
                if(tempVertex.isEmpty(s)) {
                    tempItem.setLocation(tempVertex.getName()+"/"+shelfLetter);
                    tempVertex.setAvailability(Boolean.FALSE,s);
                    tempVertex.setNewVertexItem(tempItem);
                    flag=Boolean.FALSE;
                    //System.out.println("Item : "+tempItem.getName()+" set to "+tempVertex.getName()+"/"+shelfLetter);
                }
            }
        }

    }

    public String getConfiguration() {

        JSONArray workerConfig = new JSONArray();
        for(int i=0; i<workerArray.size();i++){
            workerConfig.put(workerArray.get(i).getWorkerConfig());
        }
        JSONArray itemConfig = new JSONArray();
        for(int i=0; i<itemArray.size();i++){
            itemConfig.put(itemArray.get(i).getItemConfig());
        }

        JSONObject config = new JSONObject();
        config.put("aisles",map.getAisles());
        config.put("sections",map.getSections());
        config.put("shelves",map.getShelves());
        config.put("packagingAreas",map.getPackingAreasArray());
        config.put("workers",workerConfig);
        config.put("items",itemConfig);

        return config.toString();
    }



    //Item list Functions

    public ArrayList getItemList(){
        return itemArray;
    }

    public Object getItem(String itemId) throws JSONException {
        for (int i=0; i < itemArray.size(); i++) {
            Item o = itemArray.get(i);
            if(o.getId().equals(itemId)){
                return o;
            }
        }
        return "";
    }



    //Worker list functions

    public String getWorkerNames() throws JSONException{
        JSONArray list = new JSONArray();
        for (int i=0; i < workerArray.size(); i++) {
            Worker w = workerArray.get(i);
            list.put(w.getName());
        }
        return String.valueOf(list);
    }

    public ArrayList getWorkers() {
        return workerArray;
    }

    public Object getWorker(String workerName) throws JSONException {
        for (int i=0; i < workerArray.size(); i++) {
            Worker o = workerArray.get(i);
            if(o.getName().equals(workerName)){
                return o;
            }
        }
        return "";
    }

    public void setWorkerURI(String name, JSONObject description){
        for (int i=0; i < workerArray.size(); i++) {
            Worker o = workerArray.get(i);
            if(o.getName().equals(name)){
                String s = (String)description.get("notificationUri");
                o.setNotificationUri(s);
                break;
            }
        }
    }

    public void setWorkerAction(String name, JSONObject description) throws IOException {
        for (int i=0; i < workerArray.size(); i++) {
            Worker o = workerArray.get(i);
            if(o.getName().equals(name)){
                String type = (String)description.get("type");
                ArrayList arguments = (ArrayList) description.get("arguments");
                o.setNextAction(type, (String) arguments.get(0));
                break;
            }
        }
    }



    //Map functions

    public String getMap() {
        JSONObject newMap = new JSONObject();

        JSONArray arr1 = new JSONArray(map.getVertices());
        newMap.put("vertices",arr1);

        ArrayList<String> arr2 = new ArrayList<String>();
        for (DefaultEdge defaultEdge : map.getEdges()) {
            arr2.add(String.valueOf(defaultEdge));
        }
        newMap.put("edges",arr2);

        return String.valueOf(newMap);
    }

    public String getMapVertices() {
        JSONArray mapVertices = new JSONArray(map.getVertices());
        return String.valueOf(mapVertices);
    }

    public ArrayList<String> getOpposites(String vertexId){
        return map.getOpposites(vertexId);
    }

    public ArrayList getVertexItems(String vertex){
        return map.getVertexItems(vertex);
    }

    public String getMapPackingAreas() {
        JSONArray mapPackingAreas = new JSONArray(map.getPackingAreasArray());
        return mapPackingAreas.toString();
    }


    //Actions

    public void performAction(Worker worker) throws IOException {
        Action action = worker.getNextAction();
        //System.out.println("Starting action"+action);
        String actionType = action.getType();
        switch (actionType){
            case "move": performMoveAction(worker, action.getArgument()); break;
            case "pick": performPickAction(worker, action.getArgument()); break;
            case "drop": performDropAction(worker, action.getArgument()); break;
            case "pack": performPackAction(worker, action.getArgument()); break;
        }
        action.setSuccess(Boolean.TRUE);
        action.setStep(step);
        worker.addActionHistory(action);
        //System.out.println(worker.getActionHistory());
        worker.clearNextAction();
        updateWorkerUri(worker);
        //System.out.println("All done");
    }

    public void performMoveAction(Worker worker, String newLocation){
        //System.out.println("Inside Move action");
        worker.setLocation(newLocation);
        System.out.println("Move completed "+ worker.getName());
    }

    public void performPickAction(Worker worker, String shelf){
        for(Item item:itemArray){
            if(item.getLocation().equals(shelf)){
                worker.addWeight(item.getWeight());
                worker.addHoldingItems(item);
                break;
            }
        }
        System.out.println("Pick completed "+ worker.getName());
    }

    public void performDropAction(Worker worker, String itemName){
        //System.out.println("Inside Drop action");
        for(Item item:itemArray){
            if(item.getId().equals(itemName)){
                worker.reduceWeight(item.getWeight());
                worker.removeHoldingItems(item);
                for(Vertex v:map.getVerticesArray()){
                    if(v.getName().equals(worker.getLocation())){
                        v.setNewVertexItem(item);
                    }
                }
                break;
            }
        }
        System.out.println("Drop completed "+worker.getName());
    }

    public void performPackAction(Worker worker, String orderNo) throws IOException {
        //System.out.println("Inside Pack action");
        for(Vertex v:map.getVerticesArray()){
            if(v.getName().equals(worker.getLocation())){
                v.clearVertexItems();
            }
        }
        updateOrderGenerator(orderNo);
        System.out.println("Pack completed "+worker.getName());
    }

    public void updateOrderGenerator(String orderNo) throws IOException {
        //Send action to notificationUri
        //System.out.println("Updating OG..."+orderNo);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"status\": \"Completed\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8002/orders/"+orderNo+"/updateStatus")
                .method("PUT", body)
                .addHeader("Content-Type", "text/plain")
                .build();
        Response response = client.newCall(request).execute();
    }

    public void updateWorkerUri(Worker worker) throws IOException {
        //send POST request to worker notificationUri
        String uri = worker.getNotificationUri();
        //String action = worker.getNextAction().toString();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(uri)
                .method("PATCH", body)
                .build();
        Response response = client.newCall(request).execute();
        //System.out.println("POST sent to worker "+uri);

    }


    //Clock Sync functions

    public void newStep(JSONObject stepDescription) throws IOException {
        this.step = (int)stepDescription.get("step");
        System.out.println("Step: "+step);
        //Link Perform action

        for (int i=0; i < workerArray.size(); i++) {
            Worker o = workerArray.get(i);
            if(o.hasNextAction()){
                performAction(o);
            }
        }
    }

    public String getStep(){
        JSONObject stepObj = new JSONObject();
        stepObj.put("step",this.step);
        return stepObj.toString();
    }


}
