package br.mahlow.message.deliverer.server.rest.mapper;

import br.mahlow.message.deliverer.api.handler.mapper.MessageMapper;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Collection;

public interface BaseMessageMapper<E> extends MessageMapper<E> {

    default JsonArray toJsonArray(Collection<E> messages) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (E message : messages)
            arrayBuilder.add(toJson(message));

        return arrayBuilder.build();
    }

    JsonObject toJsonBatch(Collection<E> messages);
}
