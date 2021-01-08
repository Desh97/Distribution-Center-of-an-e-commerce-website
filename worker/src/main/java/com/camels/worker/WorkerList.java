package com.camels.worker;

import java.util.ArrayList;

public class WorkerList {
    ArrayList<Worker> workerList = new ArrayList<>();

    public void addWorker(Worker worker){
        workerList.add(worker);
    }

    public String getWorkerList() {
        return workerList.toString();
    }

    public Worker getWorkerByName(String name){
        for(Worker w: workerList){
            if(w.getName().equals(name)){
                return w;
            }
        }
        return null;
    }
}
