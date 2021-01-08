package com.camels.worker;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class WorkerServiceController  {

    WorkerService ws;

    {
        try {
            ws = new WorkerService();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Simulator

    @RequestMapping(value = "/notificationUpdate/{name}", method = RequestMethod.PATCH)
    //@ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void setConfiguration(@PathVariable String name) throws JSONException, IOException {
        //System.out.println("Inside endpoint notificationUrl");
        ws.updateNextAction(name);
    }

    //Management

    @RequestMapping(value = "/newOrder/{name}", method = RequestMethod.PUT)
    //@ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void setTask(@RequestBody String task,  @PathVariable String name) throws JSONException, IOException {
        //System.out.println("Inside endpoint newTask");
        ws.newTask(task, name);
    }
}
