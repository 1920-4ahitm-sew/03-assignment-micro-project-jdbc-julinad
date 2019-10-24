package at.htl.firedept.rest;


import at.htl.firedept.database.Database;
import at.htl.firedept.model.Firetruck;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;


@Path("firedept")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class FiretruckEndpoint {
    Database db = new Database();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Firetruck> getAllFiretrucks() throws SQLException {
        return db.getFiretrucks();
    }
    @DELETE
    @Path("{licenseplate}")
    public void deleteFiretruck(@PathParam("licenseplate") String licenseplate) throws SQLException {
        db.deleteFiretruck(licenseplate);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertFiretruck(Firetruck firetruck) throws SQLException {
        if (firetruck != null){
            db.insertFiretruck(firetruck);
        }
    }
    @PUT
    @Path("{licenseplate}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateFiretruck(@PathParam("licenseplate") String licenseplate, Firetruck firetruckUpdated) throws SQLException {
        if(firetruckUpdated != null){
            Firetruck firetruck = db.getFiretruck(licenseplate);

            firetruck.setType(firetruckUpdated.getType());
            firetruck.setNumSeats(firetruckUpdated.getNumSeats());

            db.updateFiretruck(firetruck);
        }
    }











}
