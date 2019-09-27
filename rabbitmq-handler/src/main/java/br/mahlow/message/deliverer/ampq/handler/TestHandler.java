package br.mahlow.message.deliverer.ampq.handler;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.HandlerNotificationFailed;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestHandler implements MessageHandler {

    private ConnectionFactory connectionFactory;

    @Override
    public void initializeResources() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("10011997");
    }

    @Override
    public void onNotification() throws HandlerNotificationFailed {
        try (Connection connection = connectionFactory.newConnection()) {
            try (Channel channel = connection.createChannel()) {
                channel.queueDeclare("test", true, false, false, null);
                channel.basicPublish("", "test", null, "tessdasdt".getBytes());
            }
        } catch (TimeoutException | IOException e) {
            throw new HandlerNotificationFailed(e);
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
}
