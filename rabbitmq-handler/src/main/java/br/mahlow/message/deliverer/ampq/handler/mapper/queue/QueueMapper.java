package br.mahlow.message.deliverer.ampq.handler.mapper.queue;

import br.mahlow.message.deliverer.ampq.handler.message.queue.Queue;
import br.mahlow.message.deliverer.api.handler.exception.mapper.InvalidMessageException;
import br.mahlow.message.deliverer.api.handler.mapper.MessageMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class QueueMapper implements MessageMapper<Queue> {

    @Override
    public JsonObject toJson(Queue message) {
        return Json.createObjectBuilder()
                .add("queueName", message.getQueueName())
                .add("routingKey", message.getRoutingKey())
                .add("autoDelete", message.getAutoDelete())
                .add("durable", message.getDurable())
                .add("exclusive", message.getExclusive())
                .build();
    }

    @Override
    public Queue fromJson(JsonObject jsonObject) throws InvalidMessageException {
        try {
            Queue queue = new Queue();
            queue.setQueueName(jsonObject.getString("queueName"));
            queue.setAutoDelete(jsonObject.getBoolean("autoDelete", false));
            queue.setDurable(jsonObject.getBoolean("durable", true));
            queue.setExclusive(jsonObject.getBoolean("exclusive", false));
            queue.setRoutingKey(jsonObject.getString("routingKey", null));

            return queue;
        } catch (Exception e) {
            throw new InvalidMessageException("Failed to create message. Invalid queue definition.", e);
        }
    }
}
