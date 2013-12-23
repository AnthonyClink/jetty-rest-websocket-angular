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
        persons.put("Jon Harald Søby");
        persons.put("Knut Esten Melandsø Nekså");
        persons.put("Fredrik Wilhelm Borelly");
        persons.put("Vegar Krogh Arnesten");
        return persons;
    }
}
