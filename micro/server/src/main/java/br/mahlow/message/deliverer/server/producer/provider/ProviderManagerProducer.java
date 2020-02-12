package br.mahlow.message.deliverer.server.producer.provider;

import br.mahlow.message.deliverer.server.exception.provider.FailedToShutdownProvider;
import br.mahlow.message.deliverer.server.provider.manager.ProviderManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ProviderManagerProducer {

    @Inject
    private Logger logger;

    @Produces
    @ApplicationScoped
    public ProviderManager produceProviderManager() {
        return new ProviderManager();
    }

    public void disposeProviderManager(@Disposes ProviderManager providerManager) {
        try {
            providerManager.shutdown();
        } catch (FailedToShutdownProvider exception) {
            logger.fatal("Failed to shutdown providers", exception);
        }
    }
}
