package br.mahlow.message.deliverer.api.handler;

import br.mahlow.message.deliverer.api.handler.exception.HandlerInitializationFailed;
import br.mahlow.message.deliverer.api.handler.exception.HandlerNotificationFailed;
import br.mahlow.message.deliverer.api.handler.exception.HandlerShutdownFailed;

public interface MessageHandler {

    void initializeResources() throws HandlerInitializationFailed;

    void onNotification() throws HandlerNotificationFailed;

    void shutdown() throws HandlerShutdownFailed;

    String getId();
}
