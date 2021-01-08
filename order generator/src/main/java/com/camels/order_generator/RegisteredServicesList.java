package com.camels.order_generator;

import java.util.ArrayList;

public class RegisteredServicesList {
    ArrayList<RegisteredServices> registeredList = new ArrayList<>();

    public void addService(RegisteredServices newService){
        registeredList.add(newService);
    }

    public RegisteredServices getServicesByID(int i){
        return registeredList.get(i);
    }

    public int getListSize(){
        return registeredList.size();
    }


}
