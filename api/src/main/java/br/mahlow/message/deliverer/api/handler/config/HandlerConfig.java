package br.mahlow.message.deliverer.api.handler.config;

import java.io.InputStream;
import java.util.Objects;

public class HandlerConfig {

    private InputStream source;

    private String id;

    public HandlerConfig() {
    }

    public HandlerConfig(InputStream source, String id) {
        this.source = source;
        this.id = id;
    }

    public InputStream getSource() {
        return source;
    }

    public void setSource(InputStream source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerConfig that = (HandlerConfig) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "HandlerConfig{" +
                "id='" + id + '\'' +
                '}';
    }
}
