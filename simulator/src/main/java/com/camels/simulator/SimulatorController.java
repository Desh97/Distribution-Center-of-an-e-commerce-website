package com.camels.simulator;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SimulatorController {
    Simulator dc = new Simulator();

    //Configuration Endpoints
    @RequestMapping(value = "/configuration", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void setConfiguration(@RequestBody JSONObject description) throws JSONException {
        dc.setConfiguration(description);
    }

    @RequestMapping(value = "/configuration", method = RequestMethod.GET)
    public String getConfiguration() throws JSONException {
        System.out.println("Inside getConfig...");
        return dc.getConfiguration();
    }

    //Map Endpoints
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String mapDetails()throws JSONException{
        return dc.getMap();
    }
    @RequestMapping(value = "/map/vertices", method = RequestMethod.GET)
    public String vertices() throws JSONException{
        return dc.getMapVertices();
    }

    @RequestMapping(value = "/map/vertices/{id}", method = RequestMethod.GET)
    public String vertexDetails(@PathVariable String id) throws JSONException{
        Gson gson = new Gson();
        String list = gson.toJson(dc.getVertexItems(id));
        return list;
    }

    @RequestMapping(value = "/map/vertices/{id}/opposite", method = RequestMethod.GET)
    public String vertexOpposite(@PathVariable String id) throws JSONException{
        Gson gson = new Gson();
        String list = gson.toJson(dc.getOpposites(id));
        return list;
    }

    @RequestMapping(value = "/map/packingAreas", method = RequestMethod.GET)
    public String packingAreas()throws JSONException{
        return dc.getMapPackingAreas();
    }

    //item Endpoints
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String itemList() throws JSONException{
        Gson gson = new Gson();
        String list = gson.toJson(dc.getItemList());
        return list;
    }

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    public String itemDetails (@PathVariable String id) throws JSONException {
        Gson gson = new Gson();
        String list = gson.toJson(dc.getItem(id));
        return list;

    }

    //Worker Endpoints
    @RequestMapping(value = "/workers", method = RequestMethod.GET)
    public String WorkerList() throws JSONException{
        return dc.getWorkerNames();
    }

    @RequestMapping(value = "/allWorkers", method = RequestMethod.GET)
    public String allWorkerList() throws JSONException{
        Gson gson = new Gson();
        String list = gson.toJson(dc.getWorkers());
        return list;
    }

    @RequestMapping(value = "/workers/{name}", method = RequestMethod.GET)
    public String getWorker(@PathVariable String name) throws JSONException{
        Gson gson = new Gson();
        String list = gson.toJson(dc.getWorker(name));
        return list;
    }

    @RequestMapping(value = "/workers/{name}", method = RequestMethod.PATCH)
    public void updateUri(@PathVariable String name, @RequestBody JSONObject description) throws JSONException{
        System.out.println("Updating Uri for "+name);
        dc.setWorkerURI(name, description);
    }

    @RequestMapping(value = "/workers/{name}/nextAction", method = RequestMethod.PUT)
    public void updateAction(@PathVariable String name, @RequestBody JSONObject description) throws JSONException, IOException {
        dc.setWorkerAction(name, description);
    }

    //step Endpoints
    @RequestMapping(value = "/step", method = RequestMethod.PUT)
    public void setStep(@RequestBody JSONObject step) throws IOException {
        dc.newStep(step);
    }

    @RequestMapping(value = "/step", method = RequestMethod.GET)
    public String getStep(){
        return dc.getStep();
    }
}
