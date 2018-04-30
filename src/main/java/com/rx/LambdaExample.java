package com.rx;

import com.rx.service.DataStore;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Example of lambda expression and way we should use this.
 */
public class LambdaExample {

    public  static void main(String args[]) {

        // Generic Testing


        LambdaExample lambdaExample = new LambdaExample();

        // Java generic
        Long lValue = lambdaExample.returnSameValue(new Long(4));
        Boolean iValue = lambdaExample.returnSameValue(new Boolean(true));
        String SValue = lambdaExample.returnSameValue("aaa");


        lambdaExample.Addition(a ->{ System.out.println("We are in labmda num1" +a);} ,
                b -> {System.out.println("we are in lambda num2" +b);
    });
        lambdaExample.Substraction(5,4,(a , b) -> {System.out.println(a+b);});

        System.out.println(lambdaExample.multiplication(7 , (a) -> { a = a*a; return Integer.toString(a);}));
        lambdaExample.division(a-> {System.out.println("testing" + a); return true;} , b -> { System.out.println("and"); return false;});
        DataStore dataStore = new DataStore();


        // Fetch stundent who has roll number greater than 2

        System.out.println(dataStore.filterStudent(dataStore.getStudentData() , dataStore.greaterThan5(104)));
    }

    public int Addition(Consumer<Integer> num1 ,  Consumer num2) {
        num1.accept(3);
        num2.accept(4);
        return 0;
    }

    public void Substraction(int num1 ,  int num2 , BiConsumer<Integer , Integer> a) {
        int num = num1 +num2 + 7;
        a.accept(num,num2);
    }


    public String multiplication (int num , Function<Integer, String> function) {
        return function.apply(num);
    }

    public boolean division(Predicate<Integer> num , Predicate<Integer> num1){
        return num.and(num1).test(7);
        //return num.test(7);
    }

    public <M> M returnSameValue(M obj) {
        System.out.print("return " +obj);
        return obj;
    }
}
