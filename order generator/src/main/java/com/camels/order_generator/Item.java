package com.camels.order_generator;

public class Item {
    private String itemId;
    private int weight;

    public void setItem(String itemId, int weight){
        this.itemId = itemId;
        this.weight = weight;
    }

    @Override
    public String toString(){
        return itemId;
    }

    public String getItemId(){
        return itemId;
    }

    public int getWeight() {
        return weight;
    }
}
