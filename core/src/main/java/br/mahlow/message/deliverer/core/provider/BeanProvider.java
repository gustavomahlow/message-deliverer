package br.mahlow.message.deliverer.core.provider;

public interface BeanProvider<T> {

    void initialize();

    T getInstance();

    T getInstance(String id);

    void shutdown();
}
