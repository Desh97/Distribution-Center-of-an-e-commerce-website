package com.camels.simulator;

public class Action {
    private String type;
    private String argument;
    private boolean success;
    private int step;

    public Action(String type, String argument){
        this.type = type;
        this.argument = argument;
        this.success = false;
    }

    public void setSuccess(boolean suceess){
        this.success = suceess;
    }

    public void setStep(int step){
        this.step = step;
    }

    @Override
    public String toString() {
        return ("type: " + this.getType()+" argument: "+ this.getArgument() +" success: "+ this.getSuccess() +" step: " + this.getStep());
    }

    public String getType(){return type;}

    public String getArgument(){return argument;}

    public boolean getSuccess(){return success;}

    public int getStep(){return step;}
}
