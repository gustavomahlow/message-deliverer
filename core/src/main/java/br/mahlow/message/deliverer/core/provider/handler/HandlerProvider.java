package br.mahlow.message.deliverer.core.provider.handler;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.HandlerShutdownFailed;
import br.mahlow.message.deliverer.core.annotation.Provider;
import br.mahlow.message.deliverer.core.exception.provider.FailedToShutdownProvider;
import br.mahlow.message.deliverer.core.provider.BeanProvider;

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
