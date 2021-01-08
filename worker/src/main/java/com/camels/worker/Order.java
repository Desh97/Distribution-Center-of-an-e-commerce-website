package com.camels.worker;

import java.util.ArrayList;

public class Order {
    private int orderNo;
    private String orderStatus;
    ArrayList<OrderItem> orderItemList = new ArrayList<>();

    public int getOrderNo() {
        return orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public ArrayList<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderItemList(ArrayList<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public void addItem(OrderItem i){
        orderItemList.add(i);
    }

    @Override
    public String toString(){
        return "OrderNo: "+getOrderNo();
    }
}
