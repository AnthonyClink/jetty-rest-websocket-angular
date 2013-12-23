package no.mesan.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.codehaus.jettison.json.JSONArray;

/**
 * TODO
 *
 * @author Knut Esten Melandsø Nekså
 */
@Path("/person")
public class Person {
    @GET
    public JSONArray getPersons() {
        final JSONArray persons = new JSONArray();
        persons.put("Jon Smør");
        persons.put("Vincens Lunge");
        persons.put("Ole Padde");
        persons.put("Espen Skjold");
        return persons;
    }
}
