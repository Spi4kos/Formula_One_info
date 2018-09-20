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

}

