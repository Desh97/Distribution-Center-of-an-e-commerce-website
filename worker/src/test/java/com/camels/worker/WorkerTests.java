package com.camels.worker;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WorkerTests {

    @Test
    public void newTest() throws IOException, JSONException {


        WorkerService ws = new WorkerService();
        //ws.testFunction();
        //newTask.print();
    }

    @Test
    public void graphTest(){
        Map map = new Map();
        //map.setGraph();
        //map.getShortestPath();
    }

    @Test
    public void receiveTest() throws IOException, JSONException {
        WorkerService w = new WorkerService();
        //w.setWorkerService();
    }
}
