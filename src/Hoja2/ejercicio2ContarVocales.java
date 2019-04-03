
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;


public class ejercicio2ContarVocales {

	public static int contarVocales (String palabra){
		int c= 0;
		for (int i = 0; i < palabra.length(); i++) {
			if ((palabra.charAt(i)=='a') || (palabra.charAt(i)=='e') || (palabra.charAt(i)=='i') ||  (palabra.charAt(i)=='o') || (palabra.charAt(i)=='u') || (palabra.charAt(i)=='A') || (palabra.charAt(i)=='E') || (palabra.charAt(i)=='I') || (palabra.charAt(i)=='O') || (palabra.charAt(i)=='U') ) {
				c++;
			}
		}
		
		return c;
	}
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
    	ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    	
    	long tIni = System.currentTimeMillis();
    	int contador = 0;
        String archivo = "big_file.txt";
        FileReader file = new FileReader(archivo);
        BufferedReader buf = new BufferedReader(file);
        String linea;
        //SE CREA UN ARRAY PARA ALMACENAR LO QUE QUEREMOS EJECUTAR
        List<Future<Integer>> tasks = new ArrayList<>();
        while ((linea =buf.readLine())!= null){
        	//System.out.println(linea);
        	String [] palabras = linea.split(" ");
        	//System.out.println(palabras.length);
        	for (int i = 0; i < palabras.length; i++) {
				//contador= contador+ contarVocales(palabras[i]);
        		int id = i;
        		tasks.add(pool.submit(() -> contarVocales(palabras[id])));
        	}

        }
        for (Future<Integer> task : tasks) {
			contador += task.get();
		}
        System.out.println(contador);
        double secs = (System.currentTimeMillis() - tIni) / 1000.0;
        System.out.println("Time: "+secs);//sec 0.154
    }
   
}
