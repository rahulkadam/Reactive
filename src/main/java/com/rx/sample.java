package com.rx;

import com.rx.dto.College;
import com.rx.dto.Student;
import com.rx.service.DataStore;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class sample {
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe((a) -> {
            System.out.println(a);
        });
        sample s= new sample();
        s.fetchAndprintData();
    }



    public void printStudentdata(List<Student> list) {
         System.out.println("Student " + list);
    }
    public void printCollegedata(List<College> list) {
        System.out.println("College : "+ list);
    }
    public void fetchAndprintData() {
        System.out.println("starting : ");
        //Flowable.just(new DataStore().getStudentData()).subscribe(getSubscriber());
        //System.out.println("after Student: ");
        //Flowable.just(new DataStore().getCollegeData()).subscribeOn(Schedulers.io()).observeOn(Schedulers.single()).subscribe(a -> printCollegedata(a));
  //      System.out.println("after College: ");
//        new DataStore().getResultData().subscribe(System.out::println);

//        Flowable.range(1,4).map(a -> a*a).filter(a -> a>12).delay(2, TimeUnit.SECONDS).subscribe(a -> {
  //          System.out.println(a);
   //     });

        System.out.println(new DataStore().getTopperStudent("abc" , "ac" , "def").subscribe(getGenericSubscriber()).isDisposed());

        Observable<Integer> i = Observable.range(1,3);
        Observable<Integer> a1 = Observable.range(4,3);
        Observable<Integer> a2 = Observable.merge(i,a1);
        a2.subscribe(System.out::println);

        System.out.println("after flowable");
        try {
            Thread.sleep(5000);
        }catch (Exception e) {}
    }

    public Subscriber<String> getGenericSubscriber() {
        return  new Subscriber<String>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("on Subscribe");
            }

            @Override
            public void onNext(String o) {
                System.out.println("on onNext" + o);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("on onError");
            }

            @Override
            public void onComplete() {
                System.out.println("on onComplete");
            }
        };
    }
    public Subscriber<List<Student>> getSubscriber() {
        Subscriber<List<Student>> listSubscriber = new Subscriber<List<Student>>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("on Subscribe");
            }

            @Override
            public void onNext(List<Student> o) {
                System.out.println("on onNext");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("on onError");
            }

            @Override
            public void onComplete() {
                System.out.println("on onComplete");
            }
        };
        return listSubscriber;
    }
}
