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
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class sample {

    public DataStore dataStore;
    public static void main(String[] args) {
        // Flowable.just("Hello world").subscribe((a) -> {
         //   System.out.println(a);
       // });
        sample s= new sample();
       // s.initialDataStore();
       // s.PrintObserverExample();
        int arr[] = {-1,1,-1,1};
        int originalArray[] = {1,5,2,-1};
        //int B[] = s.solutionB(5,arr);
        for (int i=0; i< arr.length ; i++) {
//            s.Combinations(arr,arr.length,i);
          //  System.out.print("  " + B[i]);
        }
     //   System.out.println(s.permuteHelper(arr,0 , originalArray , 0));
        s.fetchAndprintData();
        System.out.println("after pchecking");
    }

    private int permuteHelper(int[] arr, int index ,int[] originalArray , int osum){
        if(index >= arr.length - 1){

            System.out.print("[");
            for(int i = 0; i < arr.length - 1; i++){
                System.out.print(arr[i] + ", ");
            }
            if(arr.length > 0)
                System.out.print(arr[arr.length - 1]);
            System.out.println("]");

            int sum = 0;
            for(int i=0;i< arr.length; i++) {
                sum = sum + arr[i]*originalArray[i];
            }
            if ( osum <= sum) {
                return osum;
            }
            return sum;
        }

        int sum1 = osum;
        for(int i = index; i < arr.length; i++){ //For each index in the sub array arr[index...end]

            //Swap the elements at indices index and i
            int t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;

            //Recurse on the sub array arr[index+1...end]
            sum1 = permuteHelper(arr, index+1 , originalArray , sum1);
            System.out.println("Answer " +sum1);
            //Swap the elements back
            t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;
        }
        return sum1;
    }

    public int Combinations(int[] A , int length , int current) {
        if(length-1 == current) {
            System.out.println(A[current]);
        } else {
            System.out.print(A[current]);
            Combinations(A, length, current + 1);
            if(current+2 < length) {
                Combinations(A, length, current + 2);
            }
        }
        return 0;
    }

    public int DP(int[] A) {
        // write your code in Java SE 8
        int length = A.length;
        int S[] = new int[length];
        for(int i=0;i<length ; i++) {
            S[i] = i%2==0 ? 1: -1;
        }
        int sum =0;
        for(int i=0;i< length; i++) {
            sum = sum + A[i]*S[i];
        }
        return sum;
    }

    // you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

    public int solutionA(int[] A) {
        int length = A.length;
        int B[] = new int[length];
        for (int i = 0; i < length; i++) {
            if (A[i] > 0 && A[i] < length) {
                B[A[i]] = A[i];
            }
        }
        if (length == 1) {
            if (A[0] == 1) {
                return 2;
            }
            return 1;
        }
        for (int i = 1; i < length; i++) {
            if (B[i] == 0) {
                return i;
            }
        }
        return length + 1;
    }

    public int solution(int[] A) {
        int length = A.length;
        if (length == 1) {
            if (A[0] == 1) {
                return 2;
            }
            return 1;
        }
        for (int i = 0; i < length; i++) {
            int currentElement = A[i];
            while(currentElement > 0 && currentElement < length && A[currentElement] != currentElement) {
                int abc = A[currentElement];
                A[currentElement] = currentElement;
                currentElement = abc;
            }
        }
        for (int i = 0; i < length; i++) {
            if (A[i] != i) {
                return i;
            }
        }
        return length + 1;
    }

    /*
    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
the values of the counters after each consecutive operation will be:

    (0, 0, 1, 0, 0)
    (0, 0, 1, 1, 0)
    (0, 0, 1, 2, 0)
    (2, 2, 2, 2, 2)
    (3, 2, 2, 2, 2)
    (3, 2, 2, 3, 2)
    (3, 2, 2, 4, 2)
     */
    public int[] solutionB(int N, int[] A) {
        // write your code in Java SE 8

        int B[] = new int[N];
        int maxCounter = 0;
        for ( int i =0; i < A.length ; i++) {
            if (A[i] == N+1) {
                for(int j = 0; j< N; j++) {
                    B[j] = maxCounter;
                }
            } else {
                ++B[A[i]-1];
                if (maxCounter < B[A[i]-1]) {
                    maxCounter = B[A[i]-1];
                }
            }
        }
        return B;
    }

    public void initialDataStore() {
        dataStore = new DataStore();
    }

    public void printStudentdata(List<Student> list) {
         System.out.println("Student " + list);
         list.forEach(a -> {});
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
         // System.out.println("starting : ");
        //   Flowable.just(new DataStore().getStudentData()).subscribe(getSubscriber());
        //  System.out.println("after Student: ");

      //    Flowable.just(new DataStore().getCollegeData()).
       //           subscribeOn(Schedulers.io()).observeOn(Schedulers.single()).subscribe(a -> printCollegedata(a));


      //    System.out.println("after College: ");
      //    new DataStore().getResultData().subscribe(System.out::println);

     //     Flowable.range(1,4).map(a -> a*a).filter(a -> a>12).delay(2, TimeUnit.SECONDS).subscribe(a -> {
      //      System.out.println(a);
     //   });

     //   TestSubscriber<Integer> testSubscriber = Flowable.range(1,5).test();
  //      System.out.println(testSubscriber.values());

        Observable<String> str = new DataStore().getTopperStudent("Rahul" , "Kadam" , "Sonam");
        str.subscribeWith(getGenericSubscriber());
        str.subscribeWith(getGenericSubscriber());
        str.subscribeWith(getGenericSubscriber());
        // Data example for range and merge on observable
      //  Observable<Integer> i = Observable.range(1,3);
     //   Observable<Integer> a1 = Observable.range(4,3);
      //  Observable<Integer> a2 = Observable.merge(i,a1);
     //   a2.subscribe(System.out::println);

      //  System.out.println("after flowable");
        try {
            //Thread.sleep(5000);
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
                try {
                    Thread.sleep(5000);
                }catch (Exception e) {}
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
