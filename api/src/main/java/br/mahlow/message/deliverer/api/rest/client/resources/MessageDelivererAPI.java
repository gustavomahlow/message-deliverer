package br.mahlow.message.deliverer.api.rest.client.resources;

import br.mahlow.message.deliverer.api.rest.client.resources.notification.NotificationResource;

import javax.ws.rs.Path;

@Path("/api")
public interface MessageDelivererAPI {

    @Path("notification")
    NotificationResource notificationResource();
}
