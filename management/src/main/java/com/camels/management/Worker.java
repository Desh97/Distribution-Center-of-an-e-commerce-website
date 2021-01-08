package com.camels.management;

public class Worker {
    private String name;
    private int capacity;

    public void setWorker(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String toString(){
        return "Name: "+getName()+", Capacity: "+getCapacity();
    }
}