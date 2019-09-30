package br.mahlow.message.deliverer.ampq.handler.message;

import br.mahlow.message.deliverer.ampq.handler.message.exchange.Exchange;
import br.mahlow.message.deliverer.ampq.handler.message.queue.Queue;

public class Message {

    private Queue queue;

    private Exchange exchange;

    private Boolean mandatory;

    private Boolean immediate;

    private String message;

    public Message() {
        this.mandatory = false;
        this.immediate = false;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Boolean getImmediate() {
        return immediate;
    }

    public void setImmediate(Boolean immediate) {
        this.immediate = immediate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
