package com.camels.order_generator;

import java.util.ArrayList;

public class ItemList {
    ArrayList<Item> itemList = new ArrayList();

    public String getItemList(){
        return itemList.toString();
    }

    public Item getItem(int i){
        return itemList.get(i);
    }

    public int getSize(){
        return itemList.size();
    }

    public void addItem(Item obj){
        itemList.add(obj);
    }
}
