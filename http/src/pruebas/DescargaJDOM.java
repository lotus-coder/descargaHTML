package pruebas;

import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.*;

public class DescargaJDOM {
	private final static String [] EXTVID= {"<video", "</video>","blob:"};
	private File archivo,archivoSos;
	private URL url;
	private ArrayList<String>lineasSospechosas;
	private static final String RUTADESCARGA="archivos/";
	private Document docu;
	
	public DescargaJDOM(String urlS) {
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
	
	@SuppressWarnings("deprecation")
	public void descargaHTML() {
		 SAXBuilder builder = new SAXBuilder(true);
		 try {
			 docu = builder.build(url.toString());
			 XMLOutputter outputter = new XMLOutputter();
			 outputter.output(docu, new FileWriter(archivo));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in); 
        System.out.println("pon la url: ");
        String ruta = sc.nextLine(); 
  
    	DescargaJDOM esta = new DescargaJDOM(ruta);
    	esta.descargaHTML();
    }
}
