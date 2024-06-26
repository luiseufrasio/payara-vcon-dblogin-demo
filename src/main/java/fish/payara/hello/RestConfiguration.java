package fish.payara.hello;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures RESTful Web Services for the application.
 */
@ApplicationPath("resources")
public class RestConfiguration extends Application {
    
}
