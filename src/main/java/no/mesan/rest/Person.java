package no.mesan.rest;

import java.util.HashSet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.codehaus.jettison.json.JSONArray;

/**
 * TODO
 *
 * @author Knut Esten Melandsø Nekså
 */
@Path("/person")
public class Person {
    private static final HashSet<String> persons = new HashSet<>();
    static {
        persons.add("Jon Smør");
        persons.add("Vincens Lunge");
        persons.add("Ole Padde");
        persons.add("Espen Skjold");
    }

    @GET
    public JSONArray getPersons() {
        return new JSONArray(persons);
    }

    @POST
    @Path("/new-person")
    public void newPerson(final String newPerson) {
        persons.add(newPerson);
    }
}
