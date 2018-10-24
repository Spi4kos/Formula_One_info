package com.formula1;
import java.sql.SQLException;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        int answer;
        do {

            System.out.println("Press: 1 - Info");
            switch(scan.nextInt()){
                case 1:
                    Function.info();
                    break;
                case 1898:
                    System.out.println("Welcome to admin menu:");
                    System.out.println("1 - add driver, 2 - delete driver");
                    switch (scan.nextInt()){
                        case 1:
                            Function.addDriver();
                            break;
                        case 2:
                            Function.DeleteDriver();
                            break;
                    }

            }
            System.out.println("Press: 1 - continue, else - exit");
            answer = scan.nextInt();
        } while (answer == 1);

    }
}
