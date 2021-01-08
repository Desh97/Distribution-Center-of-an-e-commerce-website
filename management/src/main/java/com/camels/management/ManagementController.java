package com.camels.management;


import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ManagementController {

    Management m = new Management();

    //Order Generation

    @RequestMapping(value = "/newOrder", method = RequestMethod.PUT)
    public void setNewOrder(@RequestBody JSONObject description) throws JSONException, IOException, InterruptedException {
        //System.out.println("Getting new Order..."+description);
        m.setNewOrder(description);
    }

    //Worker

    @RequestMapping(value = "/newWorker", method = RequestMethod.PUT)
    public void setNewWorker(@RequestBody JSONObject description) throws JSONException, IOException, InterruptedException {
        //System.out.println("Getting worker...");
        m.setNewWorker(description);
    }
}
