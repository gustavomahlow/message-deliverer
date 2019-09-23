package br.mahlow.message.deliverer.ampq.handler;

import br.mahlow.message.deliverer.api.handler.MessageHandler;

public class TestHandler implements MessageHandler {

    @Override
    public void initializeResources() {
        System.out.println("Initialized");
    }

    @Override
    public void onNotification() {
        System.out.println("Notification");
    }

    @Override
    public void shutdown() {
        System.out.println("Shutdown");
    }

    @Override
    public String getId() {
        return "test";
    }
}
