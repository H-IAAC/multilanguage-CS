package org.example;

import org.example.janus.JanusMind;

public class Main {
    public static void main(String[] args) {
        String apiURI = "http://127.0.0.1:5002/get_memory/perceptual-memory";


        JanusMind janusMind = new JanusMind(apiURI);
        // start
        janusMind.start();

        try{
            Thread.sleep(10000); // 10 secs
        }catch (Exception e){ e.printStackTrace();}

        //stop
        janusMind.shutDown();
    }
}