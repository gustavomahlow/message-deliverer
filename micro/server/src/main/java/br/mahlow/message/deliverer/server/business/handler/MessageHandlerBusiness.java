package br.mahlow.message.deliverer.server.business.handler;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.server.exception.rest.HandlerNotFound;
import br.mahlow.message.deliverer.server.producer.context.ContextHolder;
import br.mahlow.message.deliverer.server.provider.manager.ProviderManager;
import br.mahlow.message.deliverer.server.rest.mapper.handler.MessageHandlerMapper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.constraints.NotNull;
import java.util.Collection;

import static java.util.Objects.isNull;

@Dependent
public class MessageHandlerBusiness {

    @Inject
    private ContextHolder contextHolder;

    @Inject
    private ProviderManager providerManager;

    @Inject
    private MessageHandlerMapper messageHandlerMapper;

    public void addMessageHandlerContext(@NotNull String id) {
        MessageHandler instance = providerManager.getInstance(MessageHandler.class, id);

        if (isNull(instance))
            throw new HandlerNotFound(String.format("Handler not found for id %s", id));

        contextHolder.put(MessageHandler.class, instance);
    }

    private Collection<MessageHandler> listMessageHandlerInstances() {
        return providerManager.listInstances(MessageHandler.class);
    }

    public JsonObject listMessageHandlerInstancesResponse() {
        return messageHandlerMapper.toJsonBatch(listMessageHandlerInstances());
    }
}
