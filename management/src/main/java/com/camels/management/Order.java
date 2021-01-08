package com.camels.management;

import java.util.HashMap;

public class Order {
    private int orderNo;
    private int maxWeight;
    HashMap<String,Integer> orderItems = new HashMap<>();


    public void setOrder(int orderNo, int maxWeight, HashMap orderItems){
        this.orderNo = orderNo;
        this.maxWeight = maxWeight;
        this.orderItems = orderItems;

    }


    public void setOrderItems(String newItem, int qty){
        orderItems.put(newItem, qty);
    }

    @Override
    public String toString(){
        return "OrderNo: "+orderNo+", MaxWeight: "+getMaxWeight()+", ItemList: "+orderItems.toString();
    }

    public String getOrderItems(){ return orderItems.toString(); }

    public int getOrderNo(){
        return orderNo;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}
