package com.formula1;
import java.sql.*;

public class Connectserv {
    protected String dbHost = "vitalio.mysql.tools";
    protected String dbPort = "3306";
    protected String dbUser = "vitalio_drivers";
    protected String dbPass = "vnfluax8";
    protected String dbName = "vitalio_drivers";

    //константы
    public static final String DRIVERS_TABLE = "drivers";
    public static final String DRIVERS_ID = "number";
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
    public static final String TEAMS_COUNTRY = "team_country";
    public static final String RESULTS_TABLE = "races_results";
    public static final String RESULTS_ID = "idraces_results";
    public static final String RESULTS_DRIVER = "id_drivers";
    public static final String RESULTS_RACE = "race_id";
    public static final String RESULTS_POSITION = "position";
    public static final String RESULTS_BESTLAP = "best_time";
    public static final String RESULTS_TIME = "race_time";
    public static final String RESULTS_QUAL = "race_qual";
    public static final String RESULTS_POINTS = "points";
    public static final String GP_TABLE = "grand_prix";
    public static final String GP_ID = "id_grand_prix";
    public static final String GP_COUNTRY = "country";
    public static final String GP_CITY = "city";
    public static final String GP_NAME = "name_circuit";
    public static final String GP_LAPS = "number_of_laps";
    public static final String GP_LENGTH = "length";
    public static final String GP_TURNS = "turns";




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
    public void setdrivers(int number, String fname, String lname, int team, Integer age, String country ){
        String insert = "INSERT INTO " + DRIVERS_TABLE + " (" +
                DRIVERS_ID + "," +
                DRIVERS_NAME + "," +
                DRIVERS_LASTNAME + "," +
                DRIVERS_TEAM + "," +
                DRIVERS_AGE + "," +
                DRIVERS_COUNTRY + ") VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, number);
            prSt.setString(2, fname);
            prSt.setString(3, lname);
            prSt.setInt(4, team);
            prSt.setInt(5, age);
            prSt.setString(6, country);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public ResultSet getByName (String data){
        ResultSet ress = null;

        String insert = "SELECT " +
                "dr.* , tm." + TEAMS_TITLE +
                " FROM " + DRIVERS_TABLE + " AS dr, " + TEAMS_TABLE + " AS tm WHERE " +
                "dr." + DRIVERS_TEAM + "=tm." + TEAMS_ID + " AND (dr." +
                DRIVERS_NAME + " like ? OR dr." +
                DRIVERS_LASTNAME + " like ? ) ";

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
    public void changeDrivers(int id, int newInfo, String column){
        String insert = "UPDATE " + DRIVERS_TABLE + " SET " + column + " = ?" +
                " WHERE " + DRIVERS_ID + "= ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setInt(1, newInfo);
            prSt.setInt(2, id);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet infoTeams(String title){
        ResultSet ress = null;
        String query = "%" + title + "%";
        String insert = "SELECT * FROM " + TEAMS_TABLE + " WHERE " +
                TEAMS_TITLE + " like ?" ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, query);
            ress = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ress;
    }
    public ResultSet infoGrandPrix(String title){
        ResultSet ress = null;
        String query = title.replace("-"," ");
        String insert = "SELECT * FROM " + GP_TABLE + " WHERE " +
                GP_COUNTRY + " like ?" ;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, query);
            ress = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ress;
    }
    public void setRaceResult(int number, int raceId , int position, int points, float time, String qual ){
        String insert = "INSERT INTO " + RESULTS_TABLE + " (" +
                RESULTS_DRIVER + "," +
                RESULTS_RACE + "," +
                RESULTS_POSITION + "," +
                RESULTS_POINTS + "," +
                RESULTS_TIME + "," +
                RESULTS_QUAL + ") VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, number);
            prSt.setInt(2, raceId);
            if (position==0){
                prSt.setNull(3, Types.INTEGER);
            }
            else {
                prSt.setInt(3, position);
            }
            prSt.setInt(4, points);
            prSt.setFloat(5, time);
            prSt.setString(6, qual);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public ResultSet infoRace(int idDriver,int idRace) {
        ResultSet ress = null;

        String insert = "SELECT * FROM " + RESULTS_TABLE + " WHERE " +
                RESULTS_DRIVER + " = ? AND " + RESULTS_RACE + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, idDriver);
            prSt.setInt(2, idRace);
            ress = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ress;
    }
    public void addBestTime(int id, float newInfo, String column){
        String insert = "UPDATE " + RESULTS_TABLE + " SET " + column + " = ?" +
                " WHERE " + RESULTS_ID + "= ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setFloat(1, newInfo);
            prSt.setInt(2, id);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
