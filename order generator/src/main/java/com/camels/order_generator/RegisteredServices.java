package com.camels.order_generator;

public class RegisteredServices {
    private String name;
    private String uri;

    public  RegisteredServices(String name, String uri){
        this.name = name;
        this.uri = uri;
    }

    @Override
    public String toString(){
        return "name: "+name+" , uri: "+uri;
    }

    public String getName(){
        return name;
    }

    public String getUri(){
        return uri;
    }
}
