package com.formula1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



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
    public static void addDriver(){
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


        BD.setdrivers(addName,addLastName,addTeam,addAge,addCountry);
        System.out.println("Successfully!");
    }
    public static void DeleteDriver (){
        Connectserv BD = new Connectserv();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name:");
        String field = scan.nextLine();
        System.out.println("This driver? (Yes - 1, No - 2)");
        BD.getByName(field);
        switch (scan.nextInt()){
            case 1:
                BD.delDrivers(field);
            case 2: break;
        }


    }

    private Function(){
        //запрет конструктора и создания объектов
        throw new AssertionError();
    }
}

