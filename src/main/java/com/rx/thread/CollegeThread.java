package com.rx.thread;

import com.rx.dto.College;
import com.rx.dto.Student;
import com.rx.service.DataStore;

import java.util.List;

public class CollegeThread extends Thread {
    @Override
    public void run() {
        super.run();
        for(int i =0; i < 10 ; i ++) {
            try {
//                Thread.sleep(200);
            } catch (Exception e) { }
            System.out.println("from thread 2 " +i);
        }
        DataStore dataStore = new DataStore();
        List<Student>  studentList = dataStore.filterStudentForThread(dataStore.getStudentData() , dataStore.greaterThan5(1) , 4000);
        System.out.println(studentList);
    }
}
