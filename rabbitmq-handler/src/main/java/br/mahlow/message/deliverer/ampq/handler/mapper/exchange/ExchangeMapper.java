package br.mahlow.message.deliverer.ampq.handler.mapper.exchange;

import br.mahlow.message.deliverer.ampq.handler.message.exchange.Exchange;
import br.mahlow.message.deliverer.api.handler.exception.mapper.InvalidMessageException;
import br.mahlow.message.deliverer.api.handler.mapper.MessageMapper;
import com.rabbitmq.client.BuiltinExchangeType;

import javax.json.Json;
import javax.json.JsonObject;

public class ExchangeMapper implements MessageMapper<Exchange> {

    @Override
    public JsonObject toJson(Exchange message) {
        return Json.createObjectBuilder()
                .add("exchange", message.getExchange())
                .add("internal", message.getInternal())
                .add("autoDelete", message.getAutoDelete())
                .add("durable", message.getDurable())
                .add("type", message.getType().name())
                .build();
    }

    @Override
    public Exchange fromJson(JsonObject jsonObject) throws InvalidMessageException {
        try {
            Exchange exchange = new Exchange();
            exchange.setExchange(jsonObject.getString("exchange"));
            exchange.setAutoDelete(jsonObject.getBoolean("autoDelete", false));
            exchange.setDurable(jsonObject.getBoolean("durable", true));
            exchange.setInternal(jsonObject.getBoolean("internal", false));
            exchange.setType(BuiltinExchangeType.valueOf(jsonObject.getString("type", "TOPIC")));

            return exchange;
        } catch (Exception e) {
            throw new InvalidMessageException("Failed to create message. Invalid exchange definition.", e);
        }
    }
}
