package com.newfeature;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class InvokeDynamics {

    private int id;

    public InvokeDynamics() {}
    public InvokeDynamics(Integer i) {
        System.out.println("Initializing Constructor");
    }
    public static void main(String args[]) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();


        // Initializeing Constructor
        MethodHandle MH_newArrayList1 = lookup.findConstructor(
                InvokeDynamics.class, MethodType.methodType(void.class, Integer.class));
        MH_newArrayList1.invoke(new Integer(2));


        // Initialize and calling static method onyl
        MethodHandle mh = lookup.findStatic(InvokeDynamics.class , "hello" ,
                MethodType.methodType(void.class));
        mh.invokeExact();


        // Initizlize any array List method
        MethodHandle MH_newArrayList = lookup.findConstructor(
                ArrayList.class, MethodType.methodType(void.class, Collection.class));
        Collection orig = Arrays.asList("x", "y");
        Collection copy = (ArrayList) MH_newArrayList.invokeExact(orig);

        // Setter method example
        MethodHandle setterInvoke = lookup.findSetter(
                InvokeDynamics.class, "id" , int.class);
        InvokeDynamics in = new InvokeDynamics();
        setterInvoke.invoke(in,22);
        System.out.println("Checking Setter Value" + in.id);

        MethodHandle getterInvoke = lookup.findGetter(
                InvokeDynamics.class, "id" , int.class);
        System.out.println("Checking Getter Value" + (int)getterInvoke.invoke(in));

    }

    public static void hello() {
        System.out.println("Initializing Static Hello");
    }

    public int getId() {
        System.out.println("Initializing Getter");
        return id;
    }

    public void setId(int id) {
        System.out.println("Initializing Setter");
        this.id = id;
    }
}
