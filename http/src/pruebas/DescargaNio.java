package pruebas;


	import java.net.*;
	import java.nio.channels.Channels;
	import java.nio.channels.ReadableByteChannel;
	import java.util.Scanner;
	import java.io.*;

	public class DescargaNio {

		private File archivo;
		private URL url;
		private static final String RUTADESCARGA="archivos/";
		
		public DescargaNio(String urlS) {
			if(urlS.contains("://www"))
				archivo= new File(RUTADESCARGA+urlS.substring(urlS.indexOf("://www")+7,urlS.lastIndexOf('.'))+".html");
			else
				archivo= new File(RUTADESCARGA+urlS.substring(urlS.indexOf("://")+3,urlS.lastIndexOf('.'))+".html");
			try {
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
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream(archivo);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				System.out.println("done");
				rbc.close();
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
