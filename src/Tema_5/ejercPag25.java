package Tema_5;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.*;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ejercPag25 {
	public static final String url = "https://www.aulavirtual.urjc.es";
	public static final String url1 = "https://animeflv.net/";
	public static final String url2= "https://jkanime.net/";
	public static final String url3 = "https://twitter.com";
	public static final String url4 = "http://lycanfansub.blogspot.com";
    public static final int maxPages = 20;
	
    public static Integer comprobarURL(String url){
    	String urlPage = String.format(url);
        System.out.println("Comprobando entradas de: "+urlPage);
		
        // Compruebo si me da un 200 al hacer la petición
        if (getStatusConnectionCode(urlPage) == 200) {
        	return 200;
            
        }else{
            //System.out.println("El Status Code no es OK es: "+getStatusConnectionCode(urlPage));
            return 500;
        }
    }
    public static int getStatusConnectionCode(String url) {
		
        Response response = null;
		
        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }
	
    public static void main (String args[]) throws InterruptedException {
    	List urls = new ArrayList<>();
    	urls.add(url);
    	urls.add(url1);
    	urls.add(url2);
    	urls.add(url3);
    	urls.add(url4);
    	ExecutorService pool = Executors.newCachedThreadPool();
    	 List <Future<Integer>> results = new ArrayList<>(); 
         for (int i = 0; i < urls.size(); i++) {
             String n = urls.get(i).toString() ;
             results.add((Future<Integer>) pool.submit(()-> comprobarURL(n)));
         }
         while (!results.isEmpty()){
             for (Future<Integer> result : results){
            	 Thread.sleep(5000);
                 if (result.isDone()){
                     try {
						System.out.println(result.get());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                     results.remove(result);
                     break;
                 }
             }
         }
         pool.shutdown();
       
			
    }
	
	
    /**
     * Con esta método compruebo el Status code de la respuesta que recibo al hacer la petición
     * EJM:
     * 		200 OK			300 Multiple Choices
     * 		301 Moved Permanently	305 Use Proxy
     * 		400 Bad Request		403 Forbidden
     * 		404 Not Found		500 Internal Server Error
     * 		502 Bad Gateway		503 Service Unavailable
     * @param url
     * @return Status Code
     */
    
 }
	
	
    
    
