package com.camels.simulator;

import org.json.simple.JSONObject;

public class Item {
    private String id;
    private String name;
    private String supplier;
    private int weight;
    private String location;



    public Item(String id, String name, String supplier, int weight){
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return ("id:"+this.getId()+" name: "+ this.getName() +" supplier: "+ this.getSupplier() +" weight : " + this.getWeight()+" location : " + this.getLocation());
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getSupplier(){
        return supplier;
    }

    public int getWeight(){
        return weight;
    }

    public String getLocation(){
        return location;
    }

    public JSONObject getItemConfig(){
        JSONObject itemConfig = new JSONObject();
        itemConfig.put("id",getId());
        itemConfig.put("name",getName());
        itemConfig.put("supplier",getSupplier());
        itemConfig.put("weight",getWeight());
        return itemConfig;
    }

}


