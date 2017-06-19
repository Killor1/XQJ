package introducction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class Interactivity {
	
	public static void main(String [] args) throws XQException{
		
		XQConnection conn = null;
		try{
			String desdeData = introduirDada();
			
			//pedimos los datos al usuario:
			if ("".equals(desdeData)){
				System.out.println("No s'han introduit dades!!!!!");
				System.exit(1);
			}
			//abirmos la sesion:
			conn=establirConnexio();
			System.out.println("Conexion establecida con el SGDD");
			//Creamos la XQEExpression:
			XQExpression xqe = conn.createExpression();
			
			String cad = "for $i in doc('/db/apps/demo/data/mondial.xml')/mondial/"
					+ "country[contains(government,'democracy') and " + "indep_date >= '" + desdeData + "'] \n"
					+ "return concat($i/name,' - ',$i/government,' - ',$i/indep_date)";
			
			System.out.println("Executant instruccio: \n"+cad);
			XQResultSequence xqrs = xqe.executeQuery(cad);
			
			//Mostramos los resultados uno a uno pasados a String:
			
			System.out.println("\n RESULTATS: ");
			
			while (xqrs.next()){
				System.out.println(xqrs.getItemAsString(null));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (conn!=null){
					conn.close();
				}
			}catch (XQException ex){
				ex.printStackTrace();
			}			
		}		
	}

	private static XQConnection establirConnexio() throws XQException {
		XQDataSource xqs = new ExistXQDataSource();
		xqs.setProperty("serverName", "localhost");
		xqs.setProperty("port", "8080");
		
		XQConnection conn1= xqs.getConnection("admin", "admin");
		
		return conn1;
	}

	private static String introduirDada() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text=null;
		System.out.println("Introdueix la dada a partir de la que vols consultar [aaa-mm-dd]: ");
		try{
			text=br.readLine();
		}catch (IOException e){
			System.out.println("Excepcio en la lectura de la dada: ");
			System.err.println(e);
		}		
		return text;
	}
}