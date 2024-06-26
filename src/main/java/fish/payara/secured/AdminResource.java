package fish.payara.secured;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Simply protected rest resource for role 'admin'.
 *
 */
@Path("admin")
@RolesAllowed("admin")
public class AdminResource {

    @Inject
    private SecurityContext securityContext;

    @GET
    public Response info() {
        String webName = null;
        if (securityContext.getCallerPrincipal() != null) {
            webName = securityContext.getCallerPrincipal().getName();
        }
        return Response
                .ok("Protected information for user:" + webName
                        + " | web user has role \"user\": " + securityContext.isCallerInRole("user")
                        + " | web user has role \"admin\": " + securityContext.isCallerInRole("admin"))
                .build();
    }
}

