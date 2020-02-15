package br.mahlow.message.deliverer.server.provider;

import br.mahlow.message.deliverer.server.exception.provider.FailedToInitializeProvider;
import br.mahlow.message.deliverer.server.exception.provider.FailedToShutdownProvider;

import java.util.Collection;

public interface BeanProvider<T> {

    void initialize() throws FailedToInitializeProvider;

    T getInstance();

    Collection<T> listInstances();

    T getInstance(String id);

    void shutdown() throws FailedToShutdownProvider;
}
