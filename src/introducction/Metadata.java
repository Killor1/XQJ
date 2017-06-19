package introducction;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQMetaData;

import net.xqj.exist.ExistXQDataSource;

public class Metadata {
	public static void main(String [] args) throws XQException{
		XQDataSource xqs = new ExistXQDataSource();
		System.out.println("PROPERTIES EXIST XD DATA SOURCE: ");
		for ( String property : xqs.getSupportedPropertyNames()){
			System.out.println(property);
		}
		System.out.println("==============================");
		xqs.setProperty("serverName","localhost");
		xqs.setProperty("port","8080");
		XQConnection conn = xqs.getConnection();
		
		System.out.println("METADADES DE LA CONEXIO: ");
		XQMetaData metaData = conn.getMetaData();
		System.out.println(" productname: "+metaData.getProductName());
		System.out.println(" version: "+metaData.getProductVersion());
		System.out.println(" XQJVersion: "+ metaData.getXQJVersion());		
	}
}	