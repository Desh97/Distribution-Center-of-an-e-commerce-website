package com.camels.management;

import java.util.ArrayList;

public class OrderList {
    ArrayList<Order> orderList = new ArrayList();

    public void addOrder(Order o){
        orderList.add(o);
    }

    public Order getOrder(int i){
        return orderList.get(i);
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }
}
