package com.camels.management;

import java.util.ArrayList;

public class WorkerList {
    ArrayList<Worker> workerList = new ArrayList();

    public void addWorker(Worker w){
        workerList.add(w);
    }

    public Worker getWorker(int i){
        return workerList.get(i);
    }

    public ArrayList<Worker> getWorkerList() {
        return workerList;
    }
}
