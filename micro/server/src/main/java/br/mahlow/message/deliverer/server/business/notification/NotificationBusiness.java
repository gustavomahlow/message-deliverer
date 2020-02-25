package br.mahlow.message.deliverer.server.business.notification;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.mapper.InvalidMessageException;
import br.mahlow.message.deliverer.server.exception.rest.HandlerNotificationFailed;
import br.mahlow.message.deliverer.server.exception.rest.InvalidMessage;
import br.mahlow.message.deliverer.server.pojo.notification.NotificationPOJO;
import br.mahlow.message.deliverer.server.producer.context.ContextHolder;
import br.mahlow.message.deliverer.server.rest.mapper.exception.RestExceptionMapper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Dependent
public class NotificationBusiness {

    @Inject
    private ContextHolder contextHolder;

    @Inject
    private RestExceptionMapper restExceptionMapper;

    public Object sendNotification(@NotNull JsonObject payload) {
        MessageHandler handler = contextHolder.get(MessageHandler.class);

        return sendNotification(handler, payload, false);
    }

    public Object sendNotification(@NotNull @Valid NotificationPOJO notificationPOJO) {
        return sendNotification(notificationPOJO, false);
    }

    private Object sendNotification(@NotNull @Valid NotificationPOJO notificationPOJO,
                                    Boolean ignoreErrors) {
        return sendNotification(notificationPOJO.getMessageHandler(), notificationPOJO.getMessage(), ignoreErrors);
    }

    public List<Object> sendNotificationBatch(@NotNull @Size(min = 1) @Valid Collection<NotificationPOJO> notifications,
                                              Boolean ignoreErrors) {
        List<Object> result = new ArrayList<>();

        for (NotificationPOJO notification : notifications) {
            Object notificationResult = sendNotification(notification, ignoreErrors);

            if (!Objects.isNull(notificationResult))
                result.add(notificationResult);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public Object sendNotification(@NotNull MessageHandler handler,
                                   @NotNull JsonObject payload,
                                   @NotNull Boolean parseException) {
        try {
            Object mappedMessage = handler.getMessageMapper().fromJson(payload);

            return handler.onNotification(mappedMessage);
        } catch (br.mahlow.message.deliverer.api.handler.exception.handler.HandlerNotificationFailed e) {
            HandlerNotificationFailed exc = new HandlerNotificationFailed(e);

            if (parseException)
                return restExceptionMapper.toJson(exc);

            throw exc;
        } catch (InvalidMessageException e) {
            InvalidMessage exc = new InvalidMessage(e);

            if (parseException)
                return restExceptionMapper.toJson(exc);

            throw exc;
        }
    }
}
