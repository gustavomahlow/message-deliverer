package br.mahlow.message.deliverer.api.handler;

import br.mahlow.message.deliverer.api.handler.exception.HandlerInitializationFailed;
import br.mahlow.message.deliverer.api.handler.exception.HandlerNotificationFailed;
import br.mahlow.message.deliverer.api.handler.exception.HandlerShutdownFailed;
import br.mahlow.message.deliverer.api.handler.mapper.MessageMapper;

public interface MessageHandler<E> {

    void initializeResources() throws HandlerInitializationFailed;

    void onNotification(E message) throws HandlerNotificationFailed;

    void shutdown() throws HandlerShutdownFailed;

    String getId();

    MessageMapper<E> getMessageMapper();
}
