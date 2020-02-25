package br.mahlow.message.deliverer.server.pojo.handler;

import javax.json.JsonObject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class HandlerMessagePOJO {

    @NotNull
    @Size(min = 1)
    private String id;

    @NotNull
    @Size(min = 1)
    private JsonObject message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        HandlerMessagePOJO that = (HandlerMessagePOJO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }

    @Override
    public String toString() {
        return "BatchMessagePOJO{" +
                "id='" + id + '\'' +
                ", message=" + message +
                '}';
    }
}
