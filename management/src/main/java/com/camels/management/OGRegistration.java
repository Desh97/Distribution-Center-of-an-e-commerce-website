package com.camels.management;


import okhttp3.*;

import java.io.IOException;

public class OGRegistration {

    public void registerUri() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\": \"Management Service\",\r\n    \"uri\": \"http://localhost:8003/newOrder\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8002//registry")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }



}
