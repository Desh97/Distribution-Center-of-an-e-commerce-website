package com.camels.order_generator;

import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderGeneratorController{
    OrderGenerator og;

    {
        try {
            og = new OrderGenerator();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Orders Endpoints
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders()throws JSONException {
        return og.getOrders();
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public String getOrderDetails(@PathVariable int id) throws JSONException, IOException {
        return og.getOrderDetails(id);
    }

    @RequestMapping(value = "/orders/{id}/updateStatus", method = RequestMethod.PUT)
    public void setOrderStatus(@PathVariable int id, @RequestBody String description) throws JSONException, IOException {
        og.setOrderStatus(id, description);
    }

    //Registration Endpoints
    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public void registration(@RequestBody String description) throws JSONException, InterruptedException {
        System.out.println("Service is: "+description);
        og.RegisterService(description);
    }

    @RequestMapping(value = "/step", method = RequestMethod.PUT)
    public void Step(@RequestBody String step) throws JSONException, IOException {
        System.out.println(step);
        og.clockSync();

    }
}
