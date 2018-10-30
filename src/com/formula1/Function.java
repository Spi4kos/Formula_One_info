package com.formula1;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;


public final class Function {
    public static void info() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name:");
        String field = scan.next();
        Connectserv BD = new Connectserv();
        ResultSet ress = BD.getByName(field);
        while (ress.next()){
            String name = ress.getString(Connectserv.DRIVERS_NAME);
            String lname = ress.getString(Connectserv.DRIVERS_LASTNAME);
            String team = ress.getString(Connectserv.TEAMS_TITLE);
            String nation = ress.getString(Connectserv.DRIVERS_COUNTRY);
            int age = ress.getInt(Connectserv.DRIVERS_AGE);
            System.out.println(name + " " + lname + " (" + team + "), " + age + " years old, from: " + nation );
        }

    }
    public static void addDriver() throws SQLException {
        Connectserv BD = new Connectserv();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter driver name:");
        String addName = scan.nextLine();
        System.out.println("Enter driver last name:");
        String addLastName = scan.nextLine();
        System.out.println("Enter driver team:");
        String addTeam = scan.nextLine();
        System.out.println("Enter driver country:");
        String addCountry = scan.nextLine();
        System.out.println("Enter driver age:");
        int addAge = scan.nextInt();
        System.out.println("Enter driver number:");
        int addNumber = scan.nextInt();
        ResultSet team = BD.infoTeams(addTeam);
        int addTeamId = 0;
        if(team.next()){
            addTeamId = team.getInt(Connectserv.TEAMS_ID);
        }
        BD.setdrivers(addNumber,addName,addLastName,addTeamId,addAge,addCountry);
        System.out.println("Successfully!");
    }
    public static void DeleteDriver () throws SQLException {
        Connectserv BD = new Connectserv();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name:");
        String field = scan.nextLine();
        System.out.println("This driver? (Yes - 1, No - 2)");
        ResultSet ress = BD.getByName(field);
        while (ress.next()){
            String name = ress.getString(Connectserv.DRIVERS_NAME);
            String lname = ress.getString(Connectserv.DRIVERS_LASTNAME);
            System.out.println(name + " " + lname);}
        switch (scan.nextInt()){
            case 1:
                BD.delDrivers(field);
            case 2: break;
        }


    }
    public static void changeDriver() throws SQLException {
        Connectserv BD = new Connectserv();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name:");
        String name = scan.nextLine();
        ResultSet driver = BD.getByName(name);
        int id = 0;
        if (driver.next()){
            id = driver.getInt(Connectserv.DRIVERS_ID);
        }

        System.out.println("Change: team - 1 or age - 2:");
        switch (scan.nextInt()) {
            case 1:
                scan.nextLine();
                System.out.println("Enter new team:");
                String team = scan.nextLine();
                ResultSet ress = BD.infoTeams(team);
                if (ress.next()) {
                    int teamid = ress.getInt(Connectserv.TEAMS_ID);
                    BD.changeDrivers(id, teamid, Connectserv.DRIVERS_TEAM);
                    System.out.println("Successfully!");
                }

                break;
            case 2:
                System.out.println("Enter new age:");
                int age = scan.nextInt();
                BD.changeDrivers(id,age,Connectserv.DRIVERS_AGE);
                break;
        }
    }
    public static void InfoTeam() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the title team:");
        String field = scan.nextLine();
        Connectserv BD = new Connectserv();
        ResultSet ress = BD.infoTeams(field);
        while (ress.next()){
            int id = ress.getInt(Connectserv.TEAMS_ID);
            String title = ress.getString(Connectserv.TEAMS_TITLE);
            String engine = ress.getString(Connectserv.TEAMS_ENGINE);
            String car = ress.getString(Connectserv.TEAMS_CAR);
            String team_country = ress.getString(Connectserv.TEAMS_COUNTRY);
            System.out.println(title + " (" + id + "), Car:" + car + " with engine:" + engine + ". From " +team_country  );
        }

    }

    public static void InfoGP() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the GP country:");
        String field = scan.nextLine();
        Connectserv BD = new Connectserv();
        ResultSet ress = BD.infoGrandPrix(field);
        while (ress.next()){
            int id = ress.getInt(Connectserv.GP_ID);
            String country = ress.getString(Connectserv.GP_COUNTRY);
            String city = ress.getString(Connectserv.GP_CITY);
            String name = ress.getString(Connectserv.GP_NAME);
            int laps = ress.getInt(Connectserv.GP_LAPS);
            int turns = ress.getInt(Connectserv.GP_TURNS);
            float length = ress.getFloat(Connectserv.GP_LENGTH);
            System.out.println(country + " (" + id + "), " + name + ", " + city + ". " + laps + " laps for " + length + " kilometers, " + turns + " turns."  );
        }

    }

    public static void prost() throws SQLException {
        String raceURL ="https://www.formula1.com/en/results.html/2018/races/996/united-states/fastest-laps.html";
        String[] str = raceURL.split("/");
        Connectserv BD = new Connectserv();
        int idRace;
        ResultSet ress = BD.infoGrandPrix(str[8]);
        if (ress.next()){
            idRace = ress.getInt(Connectserv.GP_ID);
            System.out.println(idRace);
        }

    }

    public static void parser() throws IOException, SQLException {
        Scanner scan = new Scanner(System.in);
        Connectserv BD = new Connectserv();

        String raceURL = scan.nextLine();
        String fastLapURL = scan.nextLine();

        int addIdRace;
        String[] str = raceURL.split("/");
        ResultSet ress = BD.infoGrandPrix(str[8]);
        addIdRace = ress.getInt(Connectserv.GP_ID);

        Document race = Jsoup.connect(raceURL).get();
        Elements table = race.select(".resultsarchive-table").select("tbody");
        Elements rows = table.select("tr");
        for (Element row: rows) {
            float FirstTime;
            float addTime;
            String addQual;
            Element cellPosition = row.selectFirst("td:nth-child(2)");
            Element cellId = row.selectFirst("td:nth-child(3)");
            Element cellTime = row.selectFirst("td:nth-child(7)");
            Element cellPts = row.selectFirst("td:nth-child(8)");
            int addPosition = Integer.parseInt(cellPosition.text());
            int addId = Integer.parseInt(cellId.text());
            int addPts = Integer.parseInt(cellPts.text());
            String timeText = cellTime.text();
            if(addPts == 1){
                String[] time = timeText.split(":");
                float hours = Float.parseFloat(time[0]);
                float minutes = Float.parseFloat(time[1]);
                float seconds = Float.parseFloat(time[2]);
                FirstTime = hours * 3600 + minutes * 60 + seconds;
                addTime = FirstTime;
                addQual = " ";
            }
            else {
                if(timeText.startsWith("lap",4)||timeText.startsWith("DNF")){
                    addQual = timeText;
                    addTime = 0;
                }
                else {
                    addTime = Float.parseFloat(timeText.substring(1).replace("s",""));
                    addQual = " ";
                }
            }
            BD.setRaceResult(addId,addIdRace,addPosition,addPts,addTime,addQual);

        }

        Document bestLap = Jsoup.connect(fastLapURL).get();
        Elements tableLap = bestLap.select(".resultsarchive-table").select("tbody");
        Elements rowsLap = tableLap.select("tr");
        for (Element row: rowsLap) {
            Element cellId = row.selectFirst("td:nth-child(3)");
            Element cellTime = row.selectFirst("td:nth-child(8)");
            int addId = Integer.parseInt(cellId.text());
            String timeText = cellTime.text();
            ResultSet info_race = BD.infoRace(addId,addIdRace);
            int idResult = info_race.getInt(Connectserv.RESULTS_ID);
            String[] time = timeText.split(":");
            float minutes = Float.parseFloat(time[0]);
            float seconds = Float.parseFloat(time[1]);
            float addTime = minutes * 60 + seconds;
            BD.addBestTime(idResult,addTime,Connectserv.RESULTS_BESTLAP);
        }


    }
    private Function(){
        //запрет конструктора и создания объектов
        throw new AssertionError();
    }
}

