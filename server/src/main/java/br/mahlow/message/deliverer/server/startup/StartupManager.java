package br.mahlow.message.deliverer.server.startup;

import br.mahlow.message.deliverer.core.exception.provider.FailedToInitializeProvider;
import br.mahlow.message.deliverer.core.provider.manager.ProviderManager;
import br.mahlow.message.deliverer.server.application.ServerApplication;
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
    private Logger logger;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            providerManager.initialize();
        } catch (FailedToInitializeProvider e) {
            logger.fatal("Failed to start provider manager", e);
        }
    }
}
