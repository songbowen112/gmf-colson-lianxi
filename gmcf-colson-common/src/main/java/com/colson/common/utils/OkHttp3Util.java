package com.colson.common.utils;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class OkHttp3Util {

    private OkHttpClient client;

    // Inject timeout properties from application.properties or application.yml
    @Value("${okHttp.connectTimeout:1000}")
    private int connectTimeout;

    @Value("${okHttp.readTimeout:1000}")
    private int readTimeout;

    @Value("${okHttp.writeTimeout:1000}")
    private int writeTimeout;

    // Initialize OkHttpClient with injected timeout values
    @PostConstruct
    private void init() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    /**
     * Execute a GET request.
     *
     * @param url    the request URL
     * @param headers optional headers to be sent with the request
     * @return the response body as a String
     * @throws IOException if an error occurs while making the request
     */
    public String get(String url, Map<String, String> headers) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(url);

        // Add headers if present
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::addHeader);
        }

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    /**
     * Execute a GET request with a JSON response mapped to a generic type.
     *
     * @param url     the request URL
     * @param headers optional headers to be sent with the request
     * @param clazz   the class of the response type
     * @param <T>     the type of the response object
     * @return the response object of type T
     * @throws IOException if an error occurs while making the request
     */
    public <T> T getForEntity(String url, Map<String, String> headers, Class<T> clazz) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(url);

        // Add headers if present
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::addHeader);
        }

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            // Use FastJson to convert the response body to the specified class type
            String responseBody = response.body().string();
            return JSON.parseObject(responseBody, clazz);
        }
    }

    /**
     * Execute a POST request with a JSON body.
     *
     * @param url        the request URL
     * @param jsonBody   the JSON body to be sent with the request
     * @param headers    optional headers to be sent with the request
     * @return the response body as a String
     * @throws IOException if an error occurs while making the request
     */
    public String post(String url, String jsonBody, Map<String, String> headers) throws IOException {
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json; charset=utf-8"));

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(body);

        // Add headers if present
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::addHeader);
        }

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    /**
     * Overloaded post method without headers (uses default headers).
     *
     * @param url      the request URL
     * @param jsonBody the JSON body to be sent with the request
     * @return the response body as a String
     * @throws IOException if an error occurs while making the request
     */
    public String post(String url, String jsonBody) throws IOException {
        return post(url, jsonBody, null); // Call the method with null headers (default headers will be used)
    }

    /**
     * Overloaded post method with headers but no body (for empty POST requests).
     *
     * @param url     the request URL
     * @param headers optional headers to be sent with the request
     * @return the response body as a String
     * @throws IOException if an error occurs while making the request
     */
    public String post(String url, Map<String, String> headers) throws IOException {
        return post(url, "{}", headers); // Send empty JSON body by default
    }

    /**
     * Execute a POST request with a JSON body and return a response as a generic type.
     *
     * @param url       the request URL
     * @param jsonBody  the JSON body to be sent with the request
     * @param headers   optional headers to be sent with the request
     * @param clazz     the class of the response type
     * @param <T>       the type of the response object
     * @return the response object of type T
     * @throws IOException if an error occurs while making the request
     */
    public <T> T postForEntity(String url, String jsonBody, Map<String, String> headers, Class<T> clazz) throws IOException {
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json; charset=utf-8"));

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(body);

        // Add headers if present, else use default
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::addHeader);
        } else {
            // Set default headers
            requestBuilder.addHeader("Content-Type", "application/json");
        }

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            // Use FastJson to convert the response body to the specified class type
            String responseBody = response.body().string();
            return JSON.parseObject(responseBody, clazz);
        }
    }

    /**
     * Overloaded method to use default headers.
     *
     * @param url      the request URL
     * @param jsonBody the JSON body to be sent with the request
     * @param clazz    the class of the response type
     * @param <T>      the type of the response object
     * @return the response object of type T
     * @throws IOException if an error occurs while making the request
     */
    public <T> T postForEntity(String url, String jsonBody, Class<T> clazz) throws IOException {
        return postForEntity(url, jsonBody, null, clazz);
    }

    /**
     * Builds a query string from the given map.
     *
     * @param params map containing query parameters
     * @return query string in the format key=value&key2=value2
     */
    private String buildQueryString(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }

        StringBuilder queryString = new StringBuilder("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            queryString.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }

        return queryString.substring(0, queryString.length() - 1); // Remove the last "&"
    }

    /**
     * Execute a GET request with query parameters.
     *
     * @param url    the request URL
     * @param params query parameters to be included in the URL
     * @param headers optional headers to be sent with the request
     * @return the response body as a String
     * @throws IOException if an error occurs while making the request
     */
    public String getWithParams(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        String fullUrl = url + buildQueryString(params);
        return get(fullUrl, headers);
    }
}