package br.mahlow.message.deliverer.server.business;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.mapper.InvalidMessageException;
import br.mahlow.message.deliverer.server.exception.rest.InvalidMessage;
import br.mahlow.message.deliverer.server.exception.rest.HandlerNotificationFailed;
import br.mahlow.message.deliverer.server.producer.context.ContextHolder;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.constraints.NotNull;

@Dependent
public class NotificationBusiness {

    @Inject
    private ContextHolder contextHolder;

    @SuppressWarnings("unchecked")
    public void sendNotification(@NotNull JsonObject payload) {
        MessageHandler handler = contextHolder.get(MessageHandler.class);

        try {
            Object mappedMessage = handler.getMessageMapper().fromJson(payload);

            handler.onNotification(mappedMessage);
        } catch (br.mahlow.message.deliverer.api.handler.exception.handler.HandlerNotificationFailed e) {
            throw new HandlerNotificationFailed("Failed to post notification", e);
        } catch (InvalidMessageException e) {
            throw new InvalidMessage("Failed to parse json payload", e);
        }
    }
}
