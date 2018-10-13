package com.formula1;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Connectserv toserv = new Connectserv();
        int answer;
        do {


            ArrayList <Athlete> ListAth = new ArrayList<>();
            ListAth.add(new Athlete(2,"Stoffel Vandoorne","McLaren",26,"Belgium"));
            ListAth.add(new Athlete(3,"Daniel Ricciardo", "Red Bull Racing", 29,"Australia"));
            ListAth.add(new Athlete(5, "Sebastian Vettel","Ferrari",31,"Germany"));
            ListAth.add(new Athlete(7, "Kimi Raikkonen", "Ferrari", 38,"Finland"));
            ListAth.add(new Athlete(8, "Romain Grosjean", "Haas F1 Team", 32, "France"));
            ListAth.add(new Athlete(9, "Marcus Ericsson", "Sauber", 28, "Sweden"));
            ListAth.add(new Athlete(10,"Pierre Gasly","Toro Rosso",22,"France"));
            ListAth.add(new Athlete(11,"Sergio Perez", "Racing Point Force India",28,"Mexico"));
            ListAth.add(new Athlete(14,"Fernando Alonso","McLaren",37,"Spain"));
            ListAth.add(new Athlete(16,"Charles Leclerc","Sauber",20,"Monaco"));
            ListAth.add(new Athlete(18,"Lance Stroll", "Williams",19,"Canada"));
            ListAth.add(new Athlete(20,"Kevin Magnussen", "Haas F1 Team",25,"Denmark"));
            ListAth.add(new Athlete(27,"Nico Hulkenberg", "Renault F1 Team",31,"Germany"));
            ListAth.add(new Athlete(28,"Brendon Hartley", "Toro Rosso",28,"New Zealand"));
            ListAth.add(new Athlete(31,"Esteban Ocon", "Racing Point Force India",21,"France"));
            ListAth.add(new Athlete(33,"Max Verstappen", "Red Bull Racing",20,"Netherlands"));
            ListAth.add(new Athlete(35,"Sergey Sirotkin", "Williams",23,"Russian Federation"));
            ListAth.add(new Athlete(44,"Lewis Hamilton", "Mercedes",33,"United Kingdom"));
            ListAth.add(new Athlete(55,"Carlos Sainz Jr.", "Renault F1 Team",24,"Spain"));
            ListAth.add(new Athlete(77,"Valtteri Bottas", "Mercedes",29,"Finland"));

            System.out.println("Press: 1 - Info, 2 - Sort 3 - Team");
            switch(scan.nextInt()){
                case 1:
                    System.out.println("Enter the name:");
                    String search = scan.next();
                    Function.search(search, ListAth);
                    break;
                case 2:
                    Function.sort(ListAth);
                case 3:System.out.println("Enter the team:");
                    String steam = scan.next();
                    Function.team(steam, ListAth);
                case 4:
                    toserv.signUp("Andrii","Hordiiev","Red Bull",21,"Ukraine");
            }
            System.out.println("Press: 1 - continue, else - exit");
            answer = scan.nextInt();
        } while (answer == 1);

    }
}
