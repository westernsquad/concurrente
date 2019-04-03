package HojaEjercicios;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class ejer3Secuencial {
	public static void sort(int[] in){//divide
	    if(in.length <2) return; //do not need to sort
	    int mid = in.length/2;
	    int left[] = new int[mid];
	    int right[] = new int[in.length-mid];
	    for(int i=0; i<mid; i++){ //copy left
	        left[i] = in[i];
	    }
	    for(int i=0; i<in.length-mid; i++){ //copy right
	        right[i] = in[mid+i];
	    }
	    sort(left);
	    sort(right);
	    merge(left, right, in);
	}

	private static void merge(int[] a, int[] b, int[] all){//junta
	    int i=0, j=0, k=0;
	    while(i<a.length && j<b.length){ //merge back
	        if(a[i] < b[j]){
	            all[k] = a[i];
	            i++;
	        }else{
	            all[k] = b[j];
	            j++;
	        }
	        k++;
	    }
	    while(i<a.length){ //left remaining
	        all[k++] = a[i++];
	    }
	    while(j<b.length){ //right remaining
	        all[k++] = b[j++];
	    }
	}

	public static void main(String[] args){
		long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
	    TInicio = System.nanoTime(); 
	    
	    int[] a = {2,3,6,4,9,22,12,1,2,6,5,4,8,3,2,1,2,2,3,6,4,9,10};
	    sort(a);    
	    for(int j=0; j<a.length; j++){
	        System.out.print(a[j] + " ");
	    }  
	    System.out.println("");
	    
	    TFin = System.nanoTime(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
	    tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
	    System.out.println("Tiempo de ejecución en milisegundos: " + tiempo ); 
	 }
}
