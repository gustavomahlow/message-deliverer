package br.mahlow.message.deliverer.core.provider;

import br.mahlow.message.deliverer.core.exception.provider.FailedToInitializeProvider;
import br.mahlow.message.deliverer.core.exception.provider.FailedToShutdownProvider;

public interface BeanProvider<T> {

    void initialize() throws FailedToInitializeProvider;

    T getInstance();

    T getInstance(String id);

    void shutdown() throws FailedToShutdownProvider;
}
