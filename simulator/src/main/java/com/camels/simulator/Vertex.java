package com.camels.simulator;

import java.util.ArrayList;
import java.util.Arrays;

public class Vertex {
    private String name;
    private int shelves;

    Boolean[] Availability = new Boolean[5];
    ArrayList<Item> vertexItems = new ArrayList();

    public Vertex(String name, int shelves){
        this.name = name;
        this.shelves = shelves;
        Arrays.fill(Availability, Boolean.TRUE);

    }

    public String getName(){
        return name;
    }

    public ArrayList getVertexItems(){
        return vertexItems;
    }

    public Boolean isEmpty(int s){
        return Availability[s - 1];
    }

    public void setAvailability(Boolean availability, int s) {

        if(s==0){
            Arrays.fill(Availability, availability);
        }
        else {
            this.Availability[s-1] = availability;
        }
    }

    public void setNewVertexItem(Item item){
        vertexItems.add(item);
    }

    public void clearVertexItems(){
        vertexItems.clear();
    }

    @Override
    public String toString() {
        return this.getName();
    }


}

