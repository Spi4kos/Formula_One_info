package com.formula1;

public class Athlete {
    int number;
    String name;
    String team;
    int age;
    String nation;
    Athlete(int number, String name, String team, int age, String nation){
        this.number = number;
        this.name = name;
        this.team = team;
        this.age = age;
        this.nation = nation;

    }

    public String getSurname() {
        String Ar[] = name.split(" ");
        return  Ar[1];
    }
}
