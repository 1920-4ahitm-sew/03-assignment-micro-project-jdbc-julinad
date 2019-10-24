package at.htl.firedept.rest;


import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Path("firedept")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class FiretruckEndpoint {

}
