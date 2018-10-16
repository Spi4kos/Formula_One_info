package com.formula1;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public final class Function {
    public static void search(String field, ArrayList <Athlete> list){
        for (int i = 0; i < list.size(); i++) {
            Athlete kent = list.get(i);
            if(kent.getName().toLowerCase().contains(field)){
                System.out.println(kent.getName() + " (" + kent.getTeam() + "), " + kent.getAge() + " years old, from: " + kent.getNation() );
            }

        }
    }
    public static void sort(ArrayList <Athlete> list){

        list.sort(Comparator.comparing(Athlete::getSurname));
        for (int i = 0; i < list.size(); i++){
            Athlete kent = list.get(i);
            System.out.println(kent.getName() + " (" + kent.getTeam() + ")" );
        }
    }
    public static void team(String field, ArrayList <Athlete> list) {
        int o = 0;
        for (int i = 0; i < list.size(); i++) {
            Athlete kent = list.get(i);

            if (kent.getTeam().toLowerCase().contains(field)){
                if (o < 1) {System.out.println(kent.getTeam() +": "); o=1;}
                System.out.println(kent.getName());

            }
        }
    }
    private Function(){
        //запрет конструктора и создания объектов
        throw new AssertionError();
    }
}

