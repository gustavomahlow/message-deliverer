package br.mahlow.message.deliverer.ampq.handler;

import br.mahlow.message.deliverer.ampq.handler.mapper.MessageMapperImpl;
import br.mahlow.message.deliverer.ampq.handler.message.Message;
import br.mahlow.message.deliverer.ampq.handler.message.exchange.Exchange;
import br.mahlow.message.deliverer.ampq.handler.message.queue.Queue;
import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerInitializationFailed;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerNotificationFailed;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerShutdownFailed;
import br.mahlow.message.deliverer.api.handler.mapper.MessageMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.isNull;

public class TestHandler implements MessageHandler<Message, JsonObject> {

    private final ThreadLocal<Channel> channelThreadLocal = new ThreadLocal<>();

    private MessageMapper<Message> messageMapper;

    private Connection connection;

    @Override
    public void initializeResources() throws HandlerInitializationFailed {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setAutomaticRecoveryEnabled(true);
            connectionFactory.setHost("localhost");
            connectionFactory.setUsername("admin");
            connectionFactory.setPassword("10011997");

            this.connection = connectionFactory.newConnection();
            this.messageMapper = new MessageMapperImpl();
        } catch (TimeoutException | IOException e) {
            throw new HandlerInitializationFailed(e);
        }
    }

    private void declareQueue(Queue queue, Channel channel) throws IOException {
        channel.queueDeclare(
                queue.getQueueName(), queue.getDurable(), queue.getExclusive(),
                queue.getAutoDelete(), queue.getArguments()
        );
    }

    private void declareExchange(Queue queue, Exchange exchange, Channel channel) throws IOException {
        channel.exchangeDeclare(
                exchange.getExchange(), exchange.getType(), exchange.getDurable(),
                exchange.getAutoDelete(), exchange.getInternal(), exchange.getArguments()
        );

        channel.queueBind(queue.getQueueName(), exchange.getExchange(), getRoutingKey(queue));
    }

    private String getRoutingKey(Queue queue) {
        if (isNull(queue.getRoutingKey()))
            return queue.getQueueName();

        return queue.getRoutingKey();
    }

    private void publish(Queue queue, Exchange exchange, Channel channel, Message message) throws IOException {
        channel.basicPublish(
                exchange.getExchange(), getRoutingKey(queue), message.getMandatory(),
                message.getImmediate(), null, message.getMessage().getBytes(UTF_8)
        );
    }

    private Channel setAndGetChannel() throws IOException {
        Channel channel = connection.createChannel();

        channelThreadLocal.set(channel);

        return channel;
    }

    @Override
    public JsonObject onNotification(Message message) throws HandlerNotificationFailed {
        try {
            Channel channel = channelThreadLocal.get();

            if (isNull(channel) || !channel.isOpen())
                channel = setAndGetChannel();

            Queue queue = message.getQueue();
            Exchange exchange = message.getExchange();

            declareQueue(message.getQueue(), channel);
            declareExchange(queue, exchange, channel);

            publish(queue, exchange, channel, message);

            return Json.createObjectBuilder()
                    .add("message", "OK")
                    .add("success", true)
                    .build();
        } catch (IOException e) {
            throw new HandlerNotificationFailed("Failed to send message to RabbitMQ", e);
        }
    }

    @Override
    public void shutdown() throws HandlerShutdownFailed {
        try {
            System.out.println("Shutdown");
            this.connection.close();
        } catch (IOException e) {
            throw new HandlerShutdownFailed(e);
        }
    }

    @Override
    public String getId() {
        return "test";
    }

    @Override
    public MessageMapper<Message> getMessageMapper() {
        return this.messageMapper;
    }
}
