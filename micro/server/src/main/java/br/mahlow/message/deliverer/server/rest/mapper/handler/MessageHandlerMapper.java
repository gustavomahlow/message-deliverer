package br.mahlow.message.deliverer.server.rest.mapper.handler;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.server.rest.mapper.BaseMessageMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.Collection;

@ApplicationScoped
public class MessageHandlerMapper implements BaseMessageMapper<MessageHandler> {

    @Override
    public JsonObject toJson(MessageHandler message) {
        return Json.createObjectBuilder()
                .add("id", message.getId())
                .build();
    }

    @Override
    public MessageHandler<?, ?> fromJson(JsonObject jsonObject) {
        return null;
    }

    @Override
    public JsonObject toJsonBatch(Collection<MessageHandler> messages) {
        return Json.createObjectBuilder()
                .add("handlers", toJsonArray(messages))
                .build();
    }
}
