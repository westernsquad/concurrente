/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente;

/**
 *
 * @author westernsquad
 */

public class PrimerosEjers {
    static volatile int resultado , num1 , num2;
    
    public static void calcu(int num1, int num2){
        new Thread(() ->{
                resultado = num1+num2;
        }).start();
           new Thread(() ->{
                resultado = num1-num2;
        }).start();
            new Thread(() ->{
                resultado = num1/num2;
        }).start();
             new Thread(() ->{
                resultado = num1*num2;
        }).start();
    }
    
    public static void escribir() {
        new Thread(() -> System.out.println("El resultado es = " + resultado )).start();
    }
     public static void main(String[] args) {
         /*Calculadora concurrente*/
         
         calcu(1,2);
         escribir();
         
                
         
        
     }
    
}


/*try {
    calculator.start()
calculatoe.join
writer.start
writer.join
catch (inter....){
e.print....*/