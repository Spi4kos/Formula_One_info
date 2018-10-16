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
    public static final String DRIVERS_NAME = "firstname";
    public static final String DRIVERS_LASTNAME = "lastname";
    public static final String DRIVERS_TEAM = "team";
    public static final String DRIVERS_AGE = "age";
    public static final String DRIVERS_COUNTRY = "country";

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
    public void signUp(String fname, String lname, String team, Integer age, String country ){
        String insert = "INSERT INTO " + DRIVERS_TABLE + " (" +
                DRIVERS_NAME + "," +
                DRIVERS_LASTNAME + "," +
                DRIVERS_TEAM + "," +
                DRIVERS_AGE + "," +
                DRIVERS_COUNTRY + ") VALUES(?,?,?,?,?)";

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
    public ResultSet getdata (String data){
        ResultSet ress = null;
        String insert = "SELECT * FROM " + DRIVERS_TABLE + " WHERE " +
                DRIVERS_NAME + "=? OR " +
                DRIVERS_LASTNAME + "=?";

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
}
