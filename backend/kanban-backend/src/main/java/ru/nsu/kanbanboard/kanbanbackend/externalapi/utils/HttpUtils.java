package ru.nsu.kanbanboard.kanbanbackend.externalapi.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpUtils {
    public static <T> CompletableFuture<T> doAndParseGETRequest(URI uri, Class<T> tClass) {
        HttpClient httpClient = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(resp -> {
                    if (resp.statusCode() != 200) {

                    }
                    return resp;
                })
                .thenApply(HttpResponse::body)
                .thenApply(s -> parseRequest(s, tClass));
    }

    private static <T> T parseRequest(String rawResp, Class<T> clazz) {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            var parsedResp = objectMapper.readValue(rawResp, clazz);
            return parsedResp;
        } catch (IOException e) {
            //
            return null;
        }
    }
}
