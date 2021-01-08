package com.camels.order_generator;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private int orderNo;
    private String orderStatus;
    private int maxWeight = 0;
    HashMap<Item , Integer> orderItems = new HashMap<>();


    public void setOrderNo(int orderNo){
        this.orderNo = orderNo;
    }

    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }

    public void setOrderItem(Item newItem, int qty){
        orderItems.put(newItem, qty);
    }

    public void setMaxWeight(int maxWeight){
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString(){
        return "OrderNo: "+orderNo+" , OrderStatus: "+orderStatus+" , OrderMaxWeight: "+maxWeight+", ItemList: "+orderItems.toString();
    }

    public String getOrderItems(){ return orderItems.toString(); }

    public int getOrderNo(){
        return orderNo;
    }

    public int getMaxWeight(){
        return maxWeight;
    }
}
