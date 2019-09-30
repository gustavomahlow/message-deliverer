package br.mahlow.message.deliverer.ampq.handler;

import br.mahlow.message.deliverer.ampq.handler.mapper.MessageMapperImpl;
import br.mahlow.message.deliverer.ampq.handler.message.Message;
import br.mahlow.message.deliverer.ampq.handler.message.exchange.Exchange;
import br.mahlow.message.deliverer.ampq.handler.message.queue.Queue;
import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.HandlerNotificationFailed;
import br.mahlow.message.deliverer.api.handler.mapper.MessageMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.isNull;

public class TestHandler implements MessageHandler<Message> {

    private ConnectionFactory connectionFactory;

    private MessageMapper<Message> messageMapper;

    @Override
    public void initializeResources() {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setAutomaticRecoveryEnabled(true);
        this.connectionFactory.setHost("localhost");
        this.connectionFactory.setUsername("admin");
        this.connectionFactory.setPassword("10011997");

        this.messageMapper = new MessageMapperImpl();
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

    @Override
    public void onNotification(Message message) throws HandlerNotificationFailed {
        try (Connection connection = connectionFactory.newConnection()) {
            try (Channel channel = connection.createChannel()) {
                Queue queue = message.getQueue();
                Exchange exchange = message.getExchange();

                declareQueue(message.getQueue(), channel);
                declareExchange(queue, exchange, channel);

                publish(queue, exchange, channel, message);
            }
        } catch (TimeoutException | IOException e) {
            throw new HandlerNotificationFailed("Failed to send message to RabbitMQ", e);
        }
    }

    @Override
    public void shutdown() {
        System.out.println("Shutdown");
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
