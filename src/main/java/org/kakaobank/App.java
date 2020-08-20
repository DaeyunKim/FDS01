package org.kakaobank;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        new Thread(()->{
           new Evaluator();
        }).start();
        new Thread(()->{
            new TransactionGenerator();
        }).start();

    }

}
