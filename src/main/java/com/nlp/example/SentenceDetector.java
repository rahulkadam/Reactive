package com.nlp.example;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SentenceDetector {

    public static void main(String args[]) throws Exception{
        InputStream ir = new FileInputStream(new File("D:\\repo\\Reactive\\src\\main\\java\\com\\nlp\\model\\da-sent.bin"));

        String sentence = " Hi. How are you? Welcome to Tutorialspoint. "
                + "We provide free tutorials on various technologies";

        SentenceModel sm = new SentenceModel(ir);

        SentenceDetectorME sd = new SentenceDetectorME(sm);

        String list[] = sd.sentDetect(sentence);

        for(String s : list) {
            System.out.println(s);
        }

        Span sp[] = sd.sentPosDetect(sentence);

        for(Span s : sp) {
            System.out.println(sentence.substring(s.getStart(), s.getEnd()));
        }
    }
}
