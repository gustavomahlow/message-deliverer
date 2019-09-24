package br.mahlow.message.deliverer.core.provider.manager;

import br.mahlow.message.deliverer.core.annotation.Provider;
import br.mahlow.message.deliverer.core.exception.provider.FailedToInitializeProvider;
import br.mahlow.message.deliverer.core.exception.provider.FailedToShutdownProvider;
import br.mahlow.message.deliverer.core.provider.BeanProvider;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
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
        Reflections reflections = new Reflections("br.mahlow.message.deliverer.core");

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
                instance.initialize();

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

    public <T> T getInstance(Class<T> clazz) {
        BeanProvider instance = instances.get(clazz);

        if (isNull(instance))
            return null;

        return clazz.cast(((BeanProvider<T>) instance).getInstance());
    }

    public <T> T getInstance(Class<T> clazz, String id) {
        BeanProvider instance = instances.get(clazz);

        if (isNull(instance))
            return null;

        return clazz.cast(((BeanProvider<T>) instance).getInstance(id));
    }

    public <T> T getProviderInstance(Class<T> clazz) {
        BeanProvider instance = providerInstances.get(clazz);

        if (isNull(instance))
            return null;

        return clazz.cast(((BeanProvider<T>) instance).getInstance());
    }

    public <T> T getProviderInstance(Class<T> clazz, String id) {
        BeanProvider instance = providerInstances.get(clazz);

        if (isNull(instance))
            return null;

        return clazz.cast(((BeanProvider<T>) instance).getInstance(id));
    }
}
