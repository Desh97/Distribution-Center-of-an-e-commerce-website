package com.camels.worker;

public class Action {
    public String type;
    public String argument;

    public Action(String type, String argument){
        this.type = type;
        this.argument = argument;
    }

    public String getType() {
        return type;
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "type='"+type+", argument= "+argument;
    }
}
