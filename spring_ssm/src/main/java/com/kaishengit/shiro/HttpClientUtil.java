package com.kaishengit.shiro;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by Acer on 2017/2/25.
 */
public class HttpClientUtil {
    public static String getHttp(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().toString();
    }

    public static String postHttp(String url,String json) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().toString();
    }
}
