package com.nlp;

public class Test {

    public  static void main(String args[]) {

        String sentence = " Hi. How are you? Welcome to Tutorialspoint. "
                + "We provide free tutorials on various technologies";

        String simple = "[.?!]";
        String[] splitString = (sentence.split(simple));
        for (String string : splitString)
            System.out.println(string);
    }
}
