package com.camels.worker;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private Order newOrder;
    private Worker newWorker;
    private int currentActionNo = 0;
    private boolean complete = Boolean.FALSE;
    private String dropLocation;
    ArrayList<Action> actionList = new ArrayList();
    Map map;

    public void setTask(Order newOrder, Worker newWorker, Map map){
        this.newOrder = newOrder;
        this.newWorker = newWorker;
        this.map = map;
        this.dropLocation = map.getPackingArea();
        calculateActionList();
    }

    public void setComplete(Boolean b){
        this.complete = b;
    }

    public Boolean isComplete(){
        return this.complete;
    }

    public void calculateActionList(){
        //Loop items: get items in order array
        ArrayList<OrderItem> itemArray = newOrder.getOrderItemList();
        //nested loop if items have multiple quantity
        for(OrderItem newItem:itemArray){
            for(int q=0; q<newItem.getQty(); q++){
                //System.out.println("Worker Location: "+newWorker.getLocation()+" Item Location: "+newItem.getItem().getLocationVertex());
                if(newWorker.getLocation().equals(newItem.getItem().getLocationVertex())){
                    saveAction("pick",newItem.getItem().getLocation());
                }
                else{
                    saveAction("move",newItem.getItem().getLocationVertex()); newWorker.setLocation(newItem.getItem().getLocationVertex());
                    saveAction("pick",newItem.getItem().getLocation());
                }
                saveAction("move",dropLocation);  newWorker.setLocation(dropLocation);
                saveAction("drop",newItem.getItem().getItemId());
            }
        }
        saveAction("pack", String.valueOf(newOrder.getOrderNo()));

        System.out.println("Order: "+newOrder.getOrderNo()+"Actions to be done: "+actionList.toString());
    }


    public void saveAction(String type, String argument){
        Action newAction = new Action(type,argument);
        if(newAction.getType().equals("move")){
            List path = map.getShortestPath(newWorker.getLocation(),argument);
            for(int i=0; i<path.size()-1;i++){
                Action a = new Action("move",path.get(i+1).toString());
                actionList.add(a);
            }
        }
        else{
            actionList.add(newAction);
        }
    }

    public Action getNextAction(){
        if(currentActionNo<actionList.size()){
            return actionList.get(currentActionNo++);
        }
        else{
            return null;
        }

    }

    public Boolean hasNextAction(){
        Boolean flag = currentActionNo<actionList.size();
        //System.out.println("Checking hasNextAction"+currentActionNo+" : "+flag);
        return flag;
    }

    public String getWorkerName(){
        return newWorker.getName();
    }

}
