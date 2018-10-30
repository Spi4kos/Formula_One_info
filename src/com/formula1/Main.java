package com.formula1;
import java.io.IOException;
import java.sql.SQLException;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        Scanner scan = new Scanner(System.in);
        int answer;
        do {

            System.out.println("Press: 1 - Info Driver");
            System.out.println("       2 - Info Team");
            switch(scan.nextInt()){
                case 1:
                    Function.info();
                    break;
                case 2:
                    Function.InfoTeam();
                    break;
                case 3:
                    //Function.InfoGP();
                    //String txt = "+3.804s";
                    //float n = Float.parseFloat(txt.substring(1).replace("s",""));
                    //System.out.println(n);
                    //Function.prost();
                    Function.parser();
                    break;
                case 1898:
                    System.out.println("Welcome to admin menu:");
                    System.out.println("1 - add driver, 2 - delete driver, 3 - change driver");
                    switch (scan.nextInt()){
                        case 1:
                            Function.addDriver();
                            break;
                        case 2:
                            Function.DeleteDriver();
                            break;
                        case 3:
                            Function.changeDriver();
                            break;
                    }

            }
            System.out.println("Press: 1 - continue, else - exit");
            answer = scan.nextInt();
        } while (answer == 1);

    }
}
