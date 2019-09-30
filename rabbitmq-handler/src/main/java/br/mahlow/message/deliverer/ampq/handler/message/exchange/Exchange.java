package br.mahlow.message.deliverer.ampq.handler.message.exchange;

import com.rabbitmq.client.BuiltinExchangeType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Exchange {

    private String exchange;

    private BuiltinExchangeType type;

    private Boolean durable;

    private Boolean autoDelete;

    private Boolean internal;

    private Map<String, Object> arguments;

    public Exchange() {
        this.durable = true;
        this.autoDelete = false;
        this.internal = false;

        this.arguments = new HashMap<>();

        this.type = BuiltinExchangeType.TOPIC;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public BuiltinExchangeType getType() {
        return type;
    }

    public void setType(BuiltinExchangeType type) {
        this.type = type;
    }

    public Boolean getDurable() {
        return durable;
    }

    public void setDurable(Boolean durable) {
        this.durable = durable;
    }

    public Boolean getAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(Boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange exchange1 = (Exchange) o;
        return Objects.equals(exchange, exchange1.exchange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchange);
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "exchange='" + exchange + '\'' +
                ", type=" + type +
                ", durable=" + durable +
                ", autoDelete=" + autoDelete +
                ", internal=" + internal +
                ", arguments=" + arguments +
                '}';
    }
}
