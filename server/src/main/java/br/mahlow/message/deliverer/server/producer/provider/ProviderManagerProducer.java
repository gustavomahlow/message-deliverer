package br.mahlow.message.deliverer.server.producer.provider;

import br.mahlow.message.deliverer.server.provider.manager.ProviderManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ProviderManagerProducer {

    @Produces
    @ApplicationScoped
    public ProviderManager produceProviderManager() {
        return new ProviderManager();
    }
}
