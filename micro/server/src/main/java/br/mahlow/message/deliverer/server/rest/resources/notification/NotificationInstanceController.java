package br.mahlow.message.deliverer.server.rest.resources.notification;

import br.mahlow.message.deliverer.server.business.NotificationBusiness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class NotificationInstanceController {

    @Inject
    private NotificationBusiness notificationBusiness;

    @POST
    public void publishMessage(@Suspended AsyncResponse asyncResponse,
                               @NotNull JsonObject payload) {
        Object result = notificationBusiness.sendNotification(payload);

        asyncResponse.resume(Response.ok(result).build());
    }
}
