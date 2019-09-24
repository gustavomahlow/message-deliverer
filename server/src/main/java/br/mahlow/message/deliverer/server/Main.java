package br.mahlow.message.deliverer.server;

import io.helidon.microprofile.server.Server;

public class Main {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");

        Server
                .create()
                .start();
    }
}
