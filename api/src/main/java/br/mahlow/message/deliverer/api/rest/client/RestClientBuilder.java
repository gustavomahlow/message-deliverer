package br.mahlow.message.deliverer.api.rest.client;

import br.mahlow.message.deliverer.api.rest.client.resources.MessageDelivererAPI;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;

import javax.ws.rs.core.UriBuilder;

public class RestClientBuilder {

    private String host;

    private int port;

    private String schema;

    public RestClientBuilder withHost(String host) {
        this.host = host;

        return this;
    }

    public RestClientBuilder withPort(int port) {
        this.port = port;

        return this;
    }

    public RestClientBuilder withSchema(String schema) {
        this.schema = schema;

        return this;
    }

    public MessageDelivererAPI build() {
        String path = String.format("%s://%s:%s", schema, host, port);

        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(path));

        return target.proxy(MessageDelivererAPI.class);
    }
}
