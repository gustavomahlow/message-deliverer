package br.mahlow.message.deliverer.api.rest.client;

import javax.json.Json;

public class TestRestClient {
    public static void main(String[] args) {
        new RestClientBuilder()
                .withHost("localhost")
                .withPort(8080)
                .withSchema("http")
                .build()
                .notificationResource()
                .notificationInstanceController("test")
                .publishMessage(
                        Json.createObjectBuilder()
                                .add("message", "Batatinha quando nasce espalha a rama pelo chão")
                                .add("queue", Json.createObjectBuilder()
                                        .add("queueName", "test"))
                                .add("exchange", Json.createObjectBuilder()
                                        .add("exchange", "test"))
                                .build()
                );
    }
}
