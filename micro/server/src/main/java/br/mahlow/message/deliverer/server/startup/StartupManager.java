package br.mahlow.message.deliverer.server.startup;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerInitializationFailed;
import br.mahlow.message.deliverer.server.business.handler.loader.MessageHandlerLoaderBusiness;
import br.mahlow.message.deliverer.server.exception.handler.FailedToLoadHandler;
import br.mahlow.message.deliverer.server.exception.handler.InvalidHandlerException;
import br.mahlow.message.deliverer.server.exception.provider.FailedToInitializeProvider;
import br.mahlow.message.deliverer.server.provider.handler.HandlerProvider;
import br.mahlow.message.deliverer.server.provider.manager.ProviderManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class StartupManager {

    @Inject
    private ProviderManager providerManager;

    @Inject
    private MessageHandlerLoaderBusiness messageHandlerLoaderBusiness;

    @Inject
    private Logger logger;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            providerManager.initialize();
            providerManager.startUp();

            initializeDefaultHandlers();
        } catch (FailedToInitializeProvider e) {
            logger.fatal("Failed to start provider manager", e);
        } catch (HandlerInitializationFailed | InvalidHandlerException | FailedToLoadHandler e) {
            logger.fatal("Failed to initialize default handlers", e);
        }
    }

    private void initializeDefaultHandlers() throws FailedToLoadHandler, InvalidHandlerException, HandlerInitializationFailed {
        HandlerProvider handlerProvider = providerManager.getProviderInstance(HandlerProvider.class);

        MessageHandler handler = messageHandlerLoaderBusiness.lookupInJar("/home/gustavo/Desenvolvimento/Git/messagedeliverer/rabbitmq-handler/target/rabbitmq-handler-1.0/rabbitmq-handler-1.0.jar");

        handlerProvider.getHandlers().put(handler.getId(), handler);
    }
}
