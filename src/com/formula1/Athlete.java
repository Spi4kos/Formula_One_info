package com.formula1;

public class Athlete {
    private int id;
    private int number;
    private String name;
    private String team;
    private int age;
    private String nation;
    Athlete(int number, String name, String team, int age, String nation){
        setNumber(number);
        setName(name);
        setTeam(team);
        setAge(age);
        setNation(nation);

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getSurname() {
        String Ar[] = name.split(" ");
        return  Ar[1];
    }

}
