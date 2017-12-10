package com.rx.thread;

import com.rx.dto.Student;
import com.rx.service.DataStore;

import java.util.List;


public class StudentThread implements Runnable {

    @Override
    public void run() {
        for(int i =0; i < 10 ; i ++) {
            try {
                Thread.sleep(20);
            } catch (Exception e) { }
            System.out.println("from thread 1 " +i);
        }
        DataStore dataStore = new DataStore();
        List<Student>  studentList = dataStore.filterStudentForThread(dataStore.getStudentData() , dataStore.greaterThan5(104) , 20);
        System.out.println(studentList);
    }
}
