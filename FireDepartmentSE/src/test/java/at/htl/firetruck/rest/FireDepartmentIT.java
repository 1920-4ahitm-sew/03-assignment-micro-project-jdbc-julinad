package at.htl.firetruck.rest;

import org.junit.Before;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class FireDepartmentIT {
    public static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db;create=true";
    static final String USER = "app";
    static final String PASSWORD = "app";
    private Client client;
    private WebTarget target;

    private static Connection connection;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/firedepartment/servlet");
    }
    @BeforeAll
    public static void initOpenDb(){
        try{
            Class.forName(DRIVER_STRING);
            connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
            //connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            //connection.setAutoCommit(true);

        } catch (ClassNotFoundException ex) {
            System.out.println("***** Couldn't open Database! *****");
            ex.printStackTrace();
            System.exit(1);
        } catch (SQLException ex) {
            System.out.println("***** Couldn't open Database! *****");
            ex.printStackTrace();
            System.exit(2);
        }
    }
    @AfterAll
    public static void initCloseDb(){
        try {
            if(connection != null || connection.isClosed() != true){
                connection.close();
                System.out.println("***** Closed Database! *****");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("insert into firetruck(type, licenseplate, numSeats) values('MKF', 'UU5678EE', 10)");

    }
    @Test
    public void testSelect() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs;

        rs = statement.executeQuery("select * from firetruck where licenseplate = 'UU5678EE'");
        assertThat(rs.next(), is(true));

    }
    @Test
    public void testUpdate() throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate("update firetruck set type='MMF' where licenseplate='UU5678EE'");

    }
    @Test
    public void testDelete() throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("delete from firetruck where licenseplate='UU5678EE'");

    }
/*    @Test
    public void crud(){
        Response response = this.target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println("payload = " + payload);
        assertThat(payload, not(empty()));

        JsonObject vehicle = payload.getJsonObject(0);
        assertThat(vehicle.getString("licenseplate"), equalTo("L666TE"));
        assertThat(vehicle.getString("type"), equalTo("KLF"));
    }*/

}
