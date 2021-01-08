package com.camels.worker;

public class OrderItem {
    private Item item;
    private Double qty;

    public OrderItem(Item item, Double qty){
        this.item = item;
        this.qty = qty;
    }

    public Item getItem() {
        return item;
    }

    public Double getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return "Item: "+getItem()+", Qty: "+getQty();
    }
}
