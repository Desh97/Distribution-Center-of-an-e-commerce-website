package com.camels.order_generator;

import java.util.ArrayList;

public class OrderList {
    ArrayList<Order> orderList = new ArrayList();

    public ArrayList<Order> getOrderList(){
        return orderList;
    }

    public Order getOrderById(int id){
        for(int i=0;i<orderList.size();i++){
            Order tempOrder = orderList.get(i);
            if(tempOrder.getOrderNo()==id){
                return tempOrder;
            }
        }
        return null;
    }

    public void addOrder(Order obj){
        orderList.add(obj);
    }
}
