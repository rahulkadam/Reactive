package com.rx.thread.executor;

public class SampleThreadProgram {
    public static void main(String args[]) {
        ExecutersService service = new ExecutersService();

        System.out.println("initialiZe");
        for(int i=0; i < 10 ; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Executing task in thread :" );
                }
            });
        }
        service.waitTillEnd();
    }
}
