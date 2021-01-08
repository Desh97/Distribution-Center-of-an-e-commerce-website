package com.camels.simulator;

import java.io.IOException;
import okhttp3.*;

public class ClockRegistration {
    public ClockRegistration() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\": \"Simulator\",\r\n    \"uri\": \"http://localhost:8080/step\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:9000/registry")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }
}
