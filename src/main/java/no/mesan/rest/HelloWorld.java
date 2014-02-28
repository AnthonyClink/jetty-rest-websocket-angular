package no.mesan.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * RESTful hello world-application using jetty.
 *
 * @author Knut Esten Melandsø Nekså
 */

@Path("/helloWorld")
public class HelloWorld {
    @GET
    public String helloWorld() {
        return "Hello world!";
    }
}
