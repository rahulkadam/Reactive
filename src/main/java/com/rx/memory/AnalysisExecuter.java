package com.rx.memory;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AnalysisExecuter {

    public static void main(String args[]) {
        int mb = 1024*1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        analysisCreateList();
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Enddd");
    }

    public static void analysisCreateList() {
        int mb = 1024*1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        int total =100_10000;
        ArrayList list = new ArrayList();
        for(int i = 0; i < total;i++) {
            AnalysisDTO d = new AnalysisDTO(i);
            list.add(d);
        }
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Enddd");
    }
}
