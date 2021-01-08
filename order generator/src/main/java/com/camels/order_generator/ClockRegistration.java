package com.camels.order_generator;

import okhttp3.*;


import java.io.IOException;

public class ClockRegistration {
    public ClockRegistration() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\": \"OrderGenerator\",\r\n    \"uri\": \"http://localhost:8002/step\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:9000/registry")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }
}
