package com.formula1;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public final class Function {
    public static void search(String field, ArrayList <Athlete> list){
        for (int i = 0; i < list.size(); i++) {
            Athlete kent = list.get(i);
            if(kent.name.toLowerCase().contains(field)){
                System.out.println(kent.name + " (" + kent.team + "), " + kent.age + " years old, from: " + kent.nation );
            }

        }
    }
    public static void sort(ArrayList <Athlete> list){

        list.sort(Comparator.comparing(Athlete::getSurname));
        for (int i = 0; i < list.size(); i++){
            Athlete kent = list.get(i);
            System.out.println(kent.name + " (" + kent.team + ")" );
        }
    }
    public static void team(String field, ArrayList <Athlete> list) {
        int o = 0;
        for (int i = 0; i < list.size(); i++) {
            Athlete kent = list.get(i);

            if (kent.team.toLowerCase().contains(field)){
                if (o < 1) {System.out.println(kent.team +": "); o=1;}
                System.out.println(kent.name);

            }
        }
    }
    private Function(){
        //запрет конструктора и создания объектов
        throw new AssertionError();
    }
}

