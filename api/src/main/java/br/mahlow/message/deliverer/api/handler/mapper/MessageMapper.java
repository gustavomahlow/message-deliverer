package br.mahlow.message.deliverer.api.handler.mapper;

import br.mahlow.message.deliverer.api.handler.exception.mapper.InvalidMessageException;

import javax.json.JsonObject;

public interface MessageMapper<E> {

    JsonObject toJson(E message);

    E fromJson(JsonObject jsonObject) throws InvalidMessageException;
}
