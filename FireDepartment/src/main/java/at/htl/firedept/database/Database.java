package at.htl.firedept.database;

import at.htl.firedept.model.Firetruck;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Database {
    public static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db";
    static final String USER = "app";
    static final String PASSWORD = "app";
    private static Connection connection;



    public Database(){

    }

    //insert
    public int insertFiretruck(Firetruck firetruck) throws SQLException {
        openDb();
        int result = 0;
        PreparedStatement insertFiretruck = connection.prepareStatement(
                "insert into Firetruck(type, licenseplate, numSeats) values (?,?,?)");
        insertFiretruck.setString(1, firetruck.getType());
        insertFiretruck.setString(2,firetruck.getLicensePlate());
        insertFiretruck.setInt(3,firetruck.getNumSeats());

        result = insertFiretruck.executeUpdate();
        closeDb();
        return result;

    }

    //select
    public List<Firetruck> getFiretrucks() throws SQLException {
        List<Firetruck> fList = new LinkedList<>();
        openDb();
        PreparedStatement select = connection.prepareStatement("select type, licenseplate, numSeats from firetruck");

        ResultSet rs = select.executeQuery();
        Firetruck firetruck;

        while (rs.next()){
            firetruck = new Firetruck();
            firetruck.setType(rs.getString(1));
            firetruck.setLicensePlate(rs.getString(2));
            firetruck.setNumSeats(rs.getInt(3));

            fList.add(firetruck);
        }
        closeDb();
        return fList;
    }
    public Firetruck getFiretruck(String licenseplate) throws SQLException {
        openDb();
        Firetruck firetruck = new Firetruck();

        PreparedStatement preparedSelect = connection.prepareStatement("select type, licenseplate, numSeats from firetruck where licenseplate=?");
        preparedSelect.setString(1, licenseplate);

        ResultSet rs = preparedSelect.executeQuery();

        if(rs.next()){
            firetruck.setType(rs.getString(1));
            firetruck.setLicensePlate(rs.getString(2));
            firetruck.setNumSeats(rs.getInt(3));
        }
        closeDb();
        return firetruck;
    }

    //update
    public void updateFiretruck(Firetruck firetruck) throws SQLException {
        openDb();
        PreparedStatement updateFiretruck = connection.prepareStatement(
                "update firetruck set type=?, numSeats? where licenseplate = ?");

        updateFiretruck.setString(1, firetruck.getType());
        updateFiretruck.setString(2,firetruck.getLicensePlate());
        updateFiretruck.setInt(3,firetruck.getNumSeats());

        updateFiretruck.execute();
        System.out.println("Firetruck with licenseplate " + firetruck.getLicensePlate() + "updated");
        closeDb();
    }
    //delete
    public Firetruck deleteFiretruck(String licenseplate) throws SQLException {
        openDb();
        PreparedStatement deleteFiretruck = connection.prepareStatement(
                "delete from firetruck where licenseplate = ?");

        deleteFiretruck.setString(1, licenseplate);

        deleteFiretruck.execute();
        System.out.println("Firetruck with licenseplate " + licenseplate + "deleted");
        closeDb();
        return null;
    }
    public static void openDb(){
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

    public static void closeDb(){
        try {
            if(connection != null || connection.isClosed() != true){
                connection.close();
                System.out.println("***** Closed Database! *****");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

