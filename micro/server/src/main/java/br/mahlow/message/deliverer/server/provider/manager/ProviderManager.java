package br.mahlow.message.deliverer.server.provider.manager;

import br.mahlow.message.deliverer.server.annotation.Provider;
import br.mahlow.message.deliverer.server.exception.provider.FailedToInitializeProvider;
import br.mahlow.message.deliverer.server.exception.provider.FailedToShutdownProvider;
import br.mahlow.message.deliverer.server.provider.BeanProvider;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;

public class ProviderManager {

    private Map<Object, BeanProvider> instances;
    private Map<Object, BeanProvider> providerInstances;

    public ProviderManager() {
        this.instances = new HashMap<>();
        this.providerInstances = new HashMap<>();
    }

    private Set<Class<? extends BeanProvider>> lookup() {
        Reflections reflections = new Reflections("br.mahlow.message.deliverer.server");

        return reflections.getSubTypesOf(BeanProvider.class);
    }

    private Class<?> getBeanProviderGeneric(Class<? extends BeanProvider> beanProviderClass) {
        return (Class) ((ParameterizedType) beanProviderClass.getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    public void initialize() throws FailedToInitializeProvider {
        try {
            Set<Class<? extends BeanProvider>> classes = lookup();

            for (Class<? extends BeanProvider> beanProviderClass : classes) {
                Class<?> providerClass = getBeanProviderGeneric(beanProviderClass);

                Provider annotation = beanProviderClass.getAnnotation(Provider.class);

                if (isNull(annotation))
                    continue;

                String annotationValue = annotation.value();

                BeanProvider instance = beanProviderClass.newInstance();

                instances.put(providerClass, instance);

                if (annotationValue.isEmpty())
                    providerInstances.put(beanProviderClass, instance);
                else
                    providerInstances.put(annotationValue, instance);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            throw new FailedToInitializeProvider("Failed to initialize provider", e);
        }
    }

    public void startUp() throws FailedToInitializeProvider {
        for (BeanProvider provider : instances.values())
            provider.initialize();
    }

    public void shutdown() throws FailedToShutdownProvider {
        for (BeanProvider provider : instances.values())
            provider.shutdown();
    }

    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> clazz) {
        BeanProvider instance = instances.get(clazz);

        if (isNull(instance))
            return null;

        return clazz.cast(((BeanProvider<T>) instance).getInstance());
    }

    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> clazz, String id) {
        BeanProvider instance = instances.get(clazz);

        if (isNull(instance))
            return null;

        return clazz.cast(((BeanProvider<T>) instance).getInstance(id));
    }

    @SuppressWarnings("unchecked")
    public <T> Collection<T> listInstances(Class<T> clazz) {
        BeanProvider instance = instances.get(clazz);

        if (isNull(instance))
            return null;

        return ((BeanProvider<T>) instance).listInstances();
    }

    public <T extends BeanProvider<?>> T getProviderInstance(Class<T> clazz) {
        BeanProvider instance = providerInstances.get(clazz);

        if (isNull(instance))
            return null;

        return clazz.cast(instance);
    }

    public <T extends BeanProvider<?>> T getProviderInstance(Class<T> clazz, String id) {
        BeanProvider instance = providerInstances.get(id);

        if (isNull(instance))
            return null;

        return clazz.cast(instance);
    }
}
