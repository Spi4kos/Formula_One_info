package com.formula1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Connectserv {
    protected String dbHost = "vitalio.mysql.tools";
    protected String dbPort = "3306";
    protected String dbUser = "vitalio_drivers";
    protected String dbPass = "vnfluax8";
    protected String dbName = "vitalio_drivers";

    //константы
    public static final String DRIVERS_TABLE = "drivers";
    public static final String DRIVERS_ID = "id";
    public static final String DRIVERS_NAME = "firstname";
    public static final String DRIVERS_LASTNAME = "lastname";
    public static final String DRIVERS_TEAM = "team_id";
    public static final String DRIVERS_AGE = "age";
    public static final String DRIVERS_COUNTRY = "country";
    public static final String TEAMS_ID = "team_id";
    public static final String TEAMS_TITLE = "title";
    public static final String TEAMS_CAR = "car";
    public static final String TEAMS_ENGINE = "engine";
    public static final String TEAMS_TABLE = "teams";

    Connection dbConnection;
    public Connection getDbConnection()
        throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" +dbName + "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        //Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;
    }
    public void setdrivers(String fname, String lname, String team, Integer age, String country ){
        String insert = "INSERT INTO " + DRIVERS_TABLE + " (" +
                DRIVERS_NAME + "," +
                DRIVERS_LASTNAME + "," +
                DRIVERS_TEAM + "," +
                DRIVERS_AGE + "," +
                DRIVERS_COUNTRY + ") VALUES(?,?,(SELECT " +
                TEAMS_ID + " FROM " + TEAMS_TABLE + " WHERE " + TEAMS_TITLE + " LIKE ?" +
                "),?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, fname);
            prSt.setString(2, lname);
            prSt.setString(3, team);
            prSt.setInt(4, age);
            prSt.setString(5, country);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public ResultSet getByName (String data){
        ResultSet ress = null;

        String insert = "SELECT " +
                "dr." + DRIVERS_NAME +
                ", dr." + DRIVERS_LASTNAME + ", dr." + DRIVERS_AGE +
                ", dr." + DRIVERS_COUNTRY + ", tm." + TEAMS_TITLE +
                " FROM " + DRIVERS_TABLE + " AS dr, " + TEAMS_TABLE + " AS tm WHERE " +
                "dr." + DRIVERS_TEAM + "=tm." + TEAMS_ID + " AND (dr." +
                DRIVERS_NAME + " like ? OR dr." +
                DRIVERS_LASTNAME + " like ? )";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, data);
            prSt.setString(2, data);
            ress = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ress;
    }
    public void delDrivers (String data){
        String insert = "DELETE FROM " + DRIVERS_TABLE +
                " WHERE " + DRIVERS_NAME + " LIKE ? OR " +
                DRIVERS_LASTNAME + " LIKE ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, data);
            prSt.setString(2, data);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
