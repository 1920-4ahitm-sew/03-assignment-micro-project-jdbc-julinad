package at.htl.firedept.database;

import at.htl.firedept.model.Firetruck;
import at.htl.firedept.rest.FiretruckEndpoint;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Database {
    static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db";
    static final String USER = "app";
    static final String PASSWORD = "app";
    static FiretruckEndpoint instance = null;
    private static Connection connection;
    public int colNum = 0;


    public Database(){

    }

    //insert
    public int insertFiretruck(Firetruck firetruck) throws SQLException {
        int result = 0;
        PreparedStatement insertFiretruck = connection.prepareStatement(
                "insert into Firetruck(type, licenseplate, numSeats) values (?,?,?)");
        insertFiretruck.setString(1, firetruck.getType());
        insertFiretruck.setString(2,firetruck.getLicensePlate());
        insertFiretruck.setInt(3,firetruck.getNumSeats());

        result = insertFiretruck.executeUpdate();
        return result;
    }

    //select
    public List<Firetruck> getFiretruck() throws SQLException {
        int i = 1;
        List<Firetruck> fList = new LinkedList<>();
        Statement statement = connection.createStatement();
        String sqlSelect = "select type, licenseplate, numSeats from Firetruck";
        ResultSet rSet = statement.executeQuery(sqlSelect);
        ResultSetMetaData rsmData = rSet.getMetaData();
        colNum = rsmData.getColumnCount();

        while(rSet.next()){
            while(i <= colNum){
                Object o = rSet.getObject(i);
                fList.add((Firetruck) o);
                i++;
            }
            i = 1;

        }
        return fList;
    }

    public Firetruck updateFiretruck(){
        return null;
    }
    public Firetruck deleteFiretruck(){
        return null;
    }
    public static void openDb(){
        connection = null;
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
            System.out.println("*****couldn't open Database! *****");
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

