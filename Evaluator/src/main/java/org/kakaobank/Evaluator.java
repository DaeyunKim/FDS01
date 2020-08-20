package org.kakaobank;

public class Evaluator {
    public Evaluator(){
        while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello Consumer");
        }
    }
}
