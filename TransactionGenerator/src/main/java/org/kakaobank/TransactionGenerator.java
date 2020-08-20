package org.kakaobank;

public class TransactionGenerator {
    public TransactionGenerator(){
        while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello Producer");
        }
    }
}
