package br.mahlow.message.deliverer.api.handler;

import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerInitializationFailed;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerNotificationFailed;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerShutdownFailed;
import br.mahlow.message.deliverer.api.handler.mapper.MessageMapper;

public interface MessageHandler<E, O> {

    void initializeResources() throws HandlerInitializationFailed;

    O onNotification(E message) throws HandlerNotificationFailed;

    void shutdown() throws HandlerShutdownFailed;

    String getId();

    MessageMapper<E> getMessageMapper();
}
