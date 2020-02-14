package br.mahlow.message.deliverer.ampq.handler.mapper;

import br.mahlow.message.deliverer.ampq.handler.mapper.exchange.ExchangeMapper;
import br.mahlow.message.deliverer.ampq.handler.mapper.queue.QueueMapper;
import br.mahlow.message.deliverer.ampq.handler.message.Message;
import br.mahlow.message.deliverer.api.handler.exception.mapper.InvalidMessageException;
import br.mahlow.message.deliverer.api.handler.mapper.MessageMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class MessageMapperImpl implements MessageMapper<Message> {

    private QueueMapper queueMapper;

    private ExchangeMapper exchangeMapper;

    public MessageMapperImpl() {
        this.queueMapper = new QueueMapper();
        this.exchangeMapper = new ExchangeMapper();
    }

    @Override
    public JsonObject toJson(Message message) {
        return Json.createObjectBuilder()
                .add("exchange", exchangeMapper.toJson(message.getExchange()))
                .add("queue", queueMapper.toJson(message.getQueue()))
                .add("immediate", message.getImmediate())
                .add("mandatory", message.getMandatory())
                .add("message", message.getMessage())
                .build();
    }

    @Override
    public Message fromJson(JsonObject jsonObject) throws InvalidMessageException {
        try {
            Message message = new Message();
            message.setExchange(exchangeMapper.fromJson(jsonObject.getJsonObject("exchange")));
            message.setQueue(queueMapper.fromJson(jsonObject.getJsonObject("queue")));
            message.setMessage(jsonObject.getString("message"));
            message.setImmediate(jsonObject.getBoolean("immediate", false));
            message.setMandatory(jsonObject.getBoolean("mandatory", false));

            return message;
        } catch (InvalidMessageException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidMessageException("Failed to create message. Invalid root object definition.", e);
        }
    }
}
