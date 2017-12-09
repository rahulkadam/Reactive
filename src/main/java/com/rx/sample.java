package com.rx;

import com.rx.dto.College;
import com.rx.dto.Student;
import com.rx.service.DataStore;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class sample {

    public DataStore dataStore;
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe((a) -> {
            System.out.println(a);
        });
        sample s= new sample();
        s.initialDataStore();
        s.PrintObserverExample();
    }

    public void initialDataStore() {
        dataStore = new DataStore();
    }

    public void printStudentdata(List<Student> list) {
         System.out.println("Student " + list);
    }
    public void printCollegedata(List<College> list) {
        System.out.println("College : "+ list);
    }


    /**
     * This method is to check and basic example of observer and observable..
     * observable is class on which we add observer which we  track using method onNext, onError and OonCOmplete..
     * method which provide us entry point is onSubscribe
     */
    public void PrintObserverExample() {

        Observable<String> observable = dataStore.getTopperStudent("Rahul" , "Kadam" , "Sonam");

        // normal observer onn observable by getting observer from new method
        TestObserver<String> observer = new TestObserver<>(); //getGenericSubscriber();
        observable.subscribeWith(observer);
        System.out.println("Observer testing " +observer.values());
        observable.subscribeWith(getGenericSubscriber());
        observable.subscribeWith(getGenericSubscriber());
        // Creating disposable Observer and using it
        observable.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("From disposable " +s);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("From disposable " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("From disposable COmplete ");
            }
        });
        // Calling consumer lambda function from observer
        observable.subscribe(a-> {} , e -> {});
        // calling observer with action and threading
        observable.subscribe(a -> {
        }, e -> {
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("from run thread");
            }
        }).dispose();
    }

    /**
     * This function is to print data and details about flowable
     */
    public void fetchAndprintData() {
          System.out.println("starting : ");
          Flowable.just(new DataStore().getStudentData()).subscribe(getSubscriber());
          System.out.println("after Student: ");
          Flowable.just(new DataStore().getCollegeData()).subscribeOn(Schedulers.io()).observeOn(Schedulers.single()).subscribe(a -> printCollegedata(a));
          System.out.println("after College: ");
          new DataStore().getResultData().subscribe(System.out::println);

          Flowable.range(1,4).map(a -> a*a).filter(a -> a>12).delay(2, TimeUnit.SECONDS).subscribe(a -> {
            System.out.println(a);
        });

        TestSubscriber<Integer> testSubscriber = Flowable.range(1,5).test();
        System.out.println(testSubscriber.values());

        new DataStore().getTopperStudent("Rahul" , "Kadam" , "Sonam").
                        subscribeWith(getGenericSubscriber());

        // Data example for range and merge on observable
        Observable<Integer> i = Observable.range(1,3);
        Observable<Integer> a1 = Observable.range(4,3);
        Observable<Integer> a2 = Observable.merge(i,a1);
        a2.subscribe(System.out::println);

        System.out.println("after flowable");
        try {
            Thread.sleep(5000);
        }catch (Exception e) {}
    }

    /**
     * This method is to return Generic Observer with type String, we can use this to manupulate string and attache it on observable
     */
    public Observer<String> getGenericSubscriber() {
        return  new Observer<String>() {

            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("Processing is starting");
            }

            @Override
            public void onNext(String o) {
                System.out.println("Student : " + o);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("on onError"+throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Processing is finished");
            }
        };
    }

    /**
     * get Subscriber generic which we use with flowable
     * @return
     */
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
