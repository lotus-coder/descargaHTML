package pruebas;

import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.nodes.Document;
import java.io.*;

public class Descarga {
	private final static String [] EXTVID= {"<video", "</video>","blob:"};
	private File archivo,archivoSos;
	private URL url;
	private ArrayList<String>lineasSospechosas;
	private static final String RUTADESCARGA="archivos/";
	private Document docu;
	
	public Descarga(String urlS) {
		if(urlS.contains("://www"))
			archivo= new File(RUTADESCARGA+urlS.substring(urlS.indexOf("://www")+7,urlS.lastIndexOf('.'))+".html");
		else
			archivo= new File(RUTADESCARGA+urlS.substring(urlS.indexOf("://")+3,urlS.lastIndexOf('.'))+".html");
		
		try {
			archivoSos	= new File("archivos/lineasSospechosas.txt");	
			archivoSos.createNewFile();
			if(!archivo.exists())
				archivo.createNewFile();
			url = new URL(urlS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void descargaHTML() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
			String linea = bf.readLine();
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
			BufferedWriter bws = new BufferedWriter(new FileWriter(archivoSos));
			lineasSospechosas = new ArrayList<String>();
			while(linea!=null) {
				bw.write(linea);
				for (int i = 0; i < EXTVID.length; i++) {
					if(linea.contains(EXTVID[i])) {
						System.out.println(linea);
						lineasSospechosas.add(linea);
						int indice=linea.indexOf(EXTVID[i]);
						bws.write(linea.substring((indice),indice+20));
						bws.newLine();
					}
				}
				bw.newLine();
				linea=bf.readLine();
			}
			System.out.println("done");
			bf.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in); 
        System.out.println("pon la url: ");
        String ruta = sc.nextLine(); 
  
    	Descarga esta = new Descarga(ruta);
    	esta.descargaHTML();
    }
}
