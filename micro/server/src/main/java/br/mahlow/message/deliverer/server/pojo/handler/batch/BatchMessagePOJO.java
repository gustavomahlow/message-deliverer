package br.mahlow.message.deliverer.server.pojo.handler.batch;

import br.mahlow.message.deliverer.server.pojo.handler.HandlerMessagePOJO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

public class BatchMessagePOJO {

    @NotNull
    @Size(min = 1)
    @Valid
    private Collection<HandlerMessagePOJO> messages;

    public Collection<HandlerMessagePOJO> getMessages() {
        return messages;
    }

    public void setMessages(Collection<HandlerMessagePOJO> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchMessagePOJO that = (BatchMessagePOJO) o;
        return Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages);
    }

    @Override
    public String toString() {
        return "BatchMessagePOJO{" +
                "messages=" + messages +
                '}';
    }
}
