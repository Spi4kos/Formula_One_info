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
                    System.out.println("Enter the name:");
                    String search = scan.next();
                    Function.info(search);
                    break;
                case 1898:
                    Function.addDriver();
            }
            System.out.println("Press: 1 - continue, else - exit");
            answer = scan.nextInt();
        } while (answer == 1);

    }
}
