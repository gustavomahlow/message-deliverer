package br.mahlow.message.deliverer.api.handler;

import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerInitializationFailed;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerNotificationFailed;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerShutdownFailed;
import br.mahlow.message.deliverer.api.handler.mapper.MessageMapper;

import javax.json.JsonObject;

public interface MessageHandler<E> {

    void initializeResources() throws HandlerInitializationFailed;

    JsonObject onNotification(E message) throws HandlerNotificationFailed;

    void shutdown() throws HandlerShutdownFailed;

    String getId();

    MessageMapper<E> getMessageMapper();
}
