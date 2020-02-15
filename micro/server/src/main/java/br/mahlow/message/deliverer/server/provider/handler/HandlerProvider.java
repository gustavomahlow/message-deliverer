package br.mahlow.message.deliverer.server.provider.handler;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerShutdownFailed;
import br.mahlow.message.deliverer.server.annotation.Provider;
import br.mahlow.message.deliverer.server.exception.provider.FailedToShutdownProvider;
import br.mahlow.message.deliverer.server.provider.BeanProvider;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Provider
public class HandlerProvider implements BeanProvider<MessageHandler> {

    private Map<String, MessageHandler> handlers;

    @Override
    public void initialize() {
        this.handlers = new HashMap<>();
    }

    @Override
    public MessageHandler getInstance() {
        return null;
    }

    @Override
    public Collection<MessageHandler> listInstances() {
        return handlers.values();
    }

    @Override
    public MessageHandler getInstance(String id) {
        return handlers.get(id);
    }

    @Override
    public void shutdown() throws FailedToShutdownProvider {
        try {
            for (MessageHandler handler : handlers.values())
                handler.shutdown();
        } catch (HandlerShutdownFailed e) {
            throw new FailedToShutdownProvider(e);
        }
    }

    public Map<String, MessageHandler> getHandlers() {
        return handlers;
    }
}
