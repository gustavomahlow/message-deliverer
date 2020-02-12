package br.mahlow.message.deliverer.server.producer.context;

import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class ContextHolder {

    private final Map<Object, Object> context = new HashMap<>();

    public <T> void put(Class<T> clazz, T instance) {
        context.put(clazz, instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz) {
        return (T) context.get(clazz);
    }
}
