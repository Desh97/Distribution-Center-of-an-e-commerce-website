package com.camels.worker;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WorkerService {

    Map map = new Map();
    ItemList itemList = new ItemList();
    WorkerList workerList = new WorkerList();
    HttpMessages newMessage = new HttpMessages();
    ArrayList<Task> taskList = new ArrayList<>();


    //Setting Service

    public WorkerService() throws IOException, JSONException {
        createMap();
        createPackingAreas();
        createItemList();
        createWorkerList();
    }



    public void createMap() throws IOException, JSONException {
        JSONObject newObject = new JSONObject(newMessage.getMap());
        JSONArray verticesArray = (JSONArray)newObject.get("vertices");
        JSONArray edgesArray = (JSONArray)newObject.get("edges");
        map.setGraph(verticesArray, edgesArray);

        System.out.println("creating map "+map.getMap());
    }

    private void createPackingAreas() throws IOException, JSONException {
        JSONArray newArray = new JSONArray(newMessage.getMapPackingAreas());
        map.setPackingAreas(newArray);
    }

    public void createItemList() throws IOException, JSONException {

        JSONArray newArray = new JSONArray(newMessage.getItemList());
        for(int i=0; i<newArray.length();i++){
            JSONObject tempObj = (JSONObject) newArray.get(i);
            String tempId = (String)tempObj.get("id");
            int tempWeight = (int) tempObj.get("weight");
            String tempLocation = (String)tempObj.get("location");
            Item tempItem = new Item();
            tempItem.setItem(tempId,tempWeight,tempLocation);
            itemList.addItem(tempItem);
        }
        System.out.println("creating item list "+itemList.getItemList());
    }

    public void createWorkerList() throws IOException, JSONException {

        JSONArray newArray = new JSONArray(newMessage.getWorkers());
        for(int i=0; i<newArray.length();i++){
            JSONObject tempObj = (JSONObject) newArray.get(i);
            String tempName = (String)tempObj.get("name");
            String tempLocation = (String) tempObj.get("location");
            int tempCapacity = (int) tempObj.get("capacity");
            Worker tempWorker = new Worker();
            tempWorker.setWorker(tempName,tempLocation,tempCapacity,Boolean.FALSE);
            workerList.addWorker(tempWorker);
            newMessage.updateWorkerUri(tempWorker.getName());
            newMessage.updateFreeWorker(tempWorker.getName(),tempWorker.getCapacity());
        }
        System.out.println("creating worker list "+workerList.getWorkerList());
    }


    //Task handling functions

    public void newTask(String task, String name) throws JSONException, IOException {

        JSONObject taskObj = new JSONObject(task);
        int ordNo = (int)taskObj.get("orderNo");
        JSONObject tempObj = (JSONObject)taskObj.get("orderItems");
        HashMap<String, Integer> newHashMap = new Gson().fromJson(tempObj.toString(), HashMap.class);

        Order o = new Order();
        o.setOrderNo(ordNo);

        Worker w = workerList.getWorkerByName(name);
        w.setAssignedForOrder(Boolean.TRUE);

        for(int i=0; i<newHashMap.size();i++){
            //finding Item in item list
            Object itemId = newHashMap.keySet().toArray()[i];
            Object tempItemQty = newHashMap.get(itemId);

            Item tempItem = itemList.getItemById(String.valueOf(itemId));

            //setting up new OrderItem
            OrderItem tempOrderItem = new OrderItem(tempItem, (Double) tempItemQty);
            o.addItem(tempOrderItem);

            //System.out.println("Printing order "+tempOrderItem.toString());
        }

        Task newTask = new Task();
        newTask.setTask(o,w,map);
        taskList.add(newTask);

        sendNextAction(newTask);

    }



    //Simulator endpoint functions

    public void updateNextAction(String name) throws IOException {
        //get the task
        //System.out.println("inside notification method");

        Task task = new Task();
        for(Task t: taskList){
            if(t.getWorkerName().equals(name) && !t.isComplete()){
                task = t;
                break;
            }
        }
        //System.out.println("inside After Loop");

        if(task.hasNextAction()){
            //System.out.println("inside IF statement");
            sendNextAction(task);
        }
        else{
            //System.out.println("inside Else statement");
            Worker w = workerList.getWorkerByName(name);
            task.setComplete(Boolean.TRUE);
            w.setAssignedForOrder(Boolean.FALSE);
            newMessage.updateFreeWorker(w.getName(),w.getCapacity());
            System.out.println("Task is complete...");
            //assign worker as free and send free worker to management
        }
    }

    public void sendNextAction(Task t) throws IOException {
        Action tempAction = t.getNextAction();
        System.out.println("Sending next action to simulator: "+tempAction.toString()+" for "+t.getWorkerName());
        newMessage.sendAction(tempAction.getType(), tempAction.getArgument(), t.getWorkerName());
    }

}
