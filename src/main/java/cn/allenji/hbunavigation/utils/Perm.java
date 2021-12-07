package cn.allenji.hbunavigation.utils;

import java.util.ArrayList;
import java.util.List;

public class Perm {
    private static List<Integer> integerList;
    private static List<List<Integer>> integerMatrix;

    public static void perm(int begin, int end){
        if (begin==end){
            integerMatrix.add(new ArrayList<>(integerList));
            return;
        }
        for (int i=begin;i<=end;i++){
            int temp=integerList.get(begin);
            integerList.set(begin,integerList.get(i));
            integerList.set(i,temp);
            perm(begin+1,end);
            temp=integerList.get(begin);
            integerList.set(begin,integerList.get(i));
            integerList.set(i,temp);
        }
    }

    public static List<List<Integer>> permList(List<Integer> list){
        integerList=list;
        integerMatrix=new ArrayList<>();
        perm(0,integerList.size()-1);
        return integerMatrix;
    }
}
