package com.camels.worker;

import java.util.ArrayList;

public class ItemList {
    ArrayList<Item> itemList = new ArrayList<>();

    public void addItem(Item item){
        itemList.add(item);
    }

    public String getItemList(){
        return itemList.toString();
    }

    public Item getItemById(String item){
        for(Item tempItem: itemList){
            if (tempItem.getItemId().equals(item)){
                return tempItem;
            }
        }
        return null;
    }

}


