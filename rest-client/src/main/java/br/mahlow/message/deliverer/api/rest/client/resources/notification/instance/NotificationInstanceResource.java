package br.mahlow.message.deliverer.api.rest.client.resources.notification.instance;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface NotificationInstanceResource {

    @POST
    void publishMessage(JsonObject payload);
}
