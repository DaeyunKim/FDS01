package org.kakaobank;

import org.kakaobank.kafka.TransactionProducer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );

        TransactionGenerator transactionGenerator = new TransactionGenerator();
        Evaluator evaluator = new Evaluator();
        try{
            new Thread(()->transactionGenerator.createLog()).start();
            new Thread(()->evaluator.evaluatorLog()).start();
        }catch(Exception e ){
            return ;
        }


    }

}
