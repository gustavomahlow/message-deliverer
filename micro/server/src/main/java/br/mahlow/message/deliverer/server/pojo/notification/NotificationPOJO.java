package br.mahlow.message.deliverer.server.pojo.notification;

import br.mahlow.message.deliverer.api.handler.MessageHandler;

import javax.json.JsonObject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class NotificationPOJO {

    @NotNull
    private MessageHandler messageHandler;

    @NotNull
    @Size(min = 1)
    private JsonObject message;

    public NotificationPOJO() {
    }

    public NotificationPOJO(MessageHandler messageHandler, JsonObject message) {
        this.messageHandler = messageHandler;
        this.message = message;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public JsonObject getMessage() {
        return message;
    }

    public void setMessage(JsonObject message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationPOJO that = (NotificationPOJO) o;
        return Objects.equals(messageHandler, that.messageHandler) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageHandler, message);
    }

    @Override
    public String toString() {
        return "NotificationPOJO{" +
                "messageHandler=" + messageHandler +
                ", message=" + message +
                '}';
    }
}
