package com.rx.service;

import com.rx.dto.College;
import com.rx.dto.Result;
import com.rx.dto.Student;
import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableRefCount;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    public List<Student> getStudentData() {
        List<Student> list = new ArrayList<>();
        try {
            Student st1 = new Student("amar", "pune", 101);
            Student st2 = new Student("sachin", "pune", 102);
            Student st3 = new Student("dada", "banglore", 103);
            Student st4 = new Student("gg", "hydrabad", 104);
            Student st5 = new Student("raina", "mumbai", 105);
            list.add(st1);
            list.add(st2);
            list.add(st3);
            list.add(st4);
            list.add(st5);
            Thread.sleep(200);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<College> getCollegeData() {
        List<College> list = new ArrayList<>();
        try {
            College st1 = new College("pict", 1, 11);
            College st2 = new College("sit", 2, 12);
            College st3 = new College("pvg", 3, 13);
            College st4 = new College("mit", 4, 14);
            College st5 = new College("coep", 5, 15);
            list.add(st1);
            list.add(st2);
            list.add(st3);
            list.add(st4);
            list.add(st5);
            Thread.sleep(200);
        } catch (Exception e) {

        }
        return list;
    }

    public Observable<String> getTopperStudent(String... nameList) {
        return Observable.create(subscriber -> {
            try {
                for (String  name: nameList) {
                    System.out.println("name in onsubscribe");
                    subscriber.onNext(name);
                }
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onComplete();
            }
        });
    }
    public Observable<List<Result>> getResultData() {
        List<Result> list = new ArrayList<>();
        try {
            Result st1 = new Result("first", 1, true);
            Result st2 = new Result("second", 2, true);
            Result st3 = new Result("third", 3, false);
            Result st4 = new Result("fourth", 4, true);
            Result st5 = new Result("fifth", 5, true);
            list.add(st1);
            list.add(st2);
            list.add(st3);
            list.add(st4);
            list.add(st5);
            Thread.sleep(200);
        } catch (Exception e) {

        }
        return Observable.just(list);
    }
}
