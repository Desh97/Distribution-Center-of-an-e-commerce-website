package com.camels.worker;

public class Worker {
    private String name;
    private String location;
    private int capacity;
    private boolean assignedForOrder;

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAssignedForOrder() {
        return assignedForOrder;
    }

    public void setWorker(String name, String location, int capacity, Boolean assignedForOrder) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.assignedForOrder = assignedForOrder;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAssignedForOrder(boolean assignedForOrder) {
        this.assignedForOrder = assignedForOrder;
    }

    @Override
    public String toString(){
        return "WorkerName: "+getName()+"WorkerLocation: "+getLocation();
    }
}
