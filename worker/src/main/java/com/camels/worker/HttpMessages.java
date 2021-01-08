package com.camels.worker;

import okhttp3.*;

import java.io.IOException;

public class HttpMessages {

    public String getItemList() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/items")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getMap() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/map")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getMapPackingAreas() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/map/packingAreas")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getWorkers() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/allWorkers/")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public void sendAction(String type, String argument, String worker) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"type\": \""+type+"\",\r\n    \"arguments\": [\r\n        \""+argument+"\"\r\n    ]\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/workers/"+worker+"/nextAction")
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }

    public void updateFreeWorker(String name, int capacity) throws IOException {
        System.out.println("Sending worker: "+name+" to Management Service");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\": \""+name+"\",\r\n    \"capacity\": "+capacity+"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8003//newWorker")
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }

    public void updateWorkerUri(String name) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"notificationUri\": \"http://localhost:8004/notificationUpdate/"+name+"\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/workers/"+name)
                .method("PATCH", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }
}