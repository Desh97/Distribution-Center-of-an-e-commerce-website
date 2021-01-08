package com.camels.worker;

public class Item {
    private String itemId;
    //private String itemName;
    private int weight;
    private String location;
    //private int qty=0;

    public String getItemId() {
        return itemId;
    }

//    public String getItemName() {
//        return itemName;
//    }

    public int getWeight() {
        return weight;
    }

    public String getLocation() {
        return location;
    }

    public String getLocationVertex() {
        return location.substring(0,4);
    }


    public void setItem(String itemID, int weight, String location){
        this.itemId=itemID;
        //this.itemName = itemName;
        this.weight = weight;
        this.location = location;
        //this.qty = qty;
    }

    @Override
    public String toString(){
        return "ItemName: "+getItemId()+", Weight: "+getWeight()+ ", Location: "+getLocation();
    }
}

