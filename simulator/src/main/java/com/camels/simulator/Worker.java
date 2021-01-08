package com.camels.simulator;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Worker {
    private String name;
    private int capacity;
    private String location;
    private int weight;
    private String notificationUri;
    private Action nextAction;

    ArrayList<Item> holdingItems = new ArrayList<Item>();
    ArrayList<Action> actionHistory = new ArrayList();

    //Setter methods

//    public Worker(String name, String location, int capacity ){
//        this.name = name;
//        this.location = location;
//        this.capacity = capacity;
//        this.weight = 0;
//    }

    public void setLocation(String location){
        this.location = location;
    }

    public void addWeight(int weight){
        this.weight = this.weight + weight;
    }

    public void reduceWeight(int weight){
        this.weight = this.weight - weight;
    }

    public void addHoldingItems(Item item){
        //System.out.println("Updating Holding items... ");
        holdingItems.add(item);
    }

    public void removeHoldingItems(Item item){
        holdingItems.removeIf(tempItem -> tempItem.equals(item));
    }

    public void setNotificationUri(String notificationUri){
        this.notificationUri = notificationUri;
    }

    public void setNextAction(String type, String arguments) {
        Action action = new Action(type, arguments);
        this.nextAction = action;
    }

    public void addActionHistory(Action action){
        this.actionHistory.add(action);
    }

    public void clearNextAction(){
        nextAction = null;
    }


    //Getter methods

    public String getName(){
        return name;
    }

    public int getCapacity(){
        return capacity;
    }

    public String getLocation(){
        return location;
    }

    public int getWeight(){
        return weight;
    }

    public String getNotificationUri(){
        return notificationUri;
    }

    public Boolean hasNextAction(){
        return !(nextAction ==null);
    }

    public Action getNextAction(){ return nextAction;}

    public ArrayList<Action> getActionHistory(){ return actionHistory;}

    @Override
    public String toString() {
        return ("name:"+this.getName()+" capacity: "+ this.getCapacity() +" location: "+ this.getLocation()+" holdingWeight: "+ this.getWeight()+" notificationUri: "+ this.getNotificationUri()+" nextAction: "+ this.getNextAction());
    }

    public JSONObject getWorkerConfig(){
        JSONObject workerConfig = new JSONObject();
        workerConfig.put("name",getName());
        workerConfig.put("location",getLocation());
        workerConfig.put("capacity",getCapacity());
        return workerConfig;
    }


//    public void performAction(){
//        String type = nextAction.getType();
//        String arguments = nextAction.getArgument();
//        //Implement action here
//        nextAction.setSuccess(true);
//    }
}


