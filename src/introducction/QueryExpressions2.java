package introducction;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class QueryExpressions2 {
	public static void main(String [] args) throws XQException, XMLStreamException{
		XQDataSource xqs = new ExistXQDataSource();
		xqs.setProperty("serverName", "localhost");
		xqs.setProperty("port", "8080");
		
		XQConnection conn= xqs.getConnection();
		XQExpression xqe = conn.createExpression();
		String cad1 = "doc('/db/apps/demo/data/mondial.xml')/mondial/country/name";
		System.out.println("Executant instruccio: "+cad1);
		
		XQResultSequence xqrs =xqe.executeQuery(cad1);
		System.out.println("\nResultats: ");
		while(xqrs.next()){
			XMLStreamReader xsr = xqrs.getItemAsStream();
			for(;xsr.hasNext();xsr.next())tractarNom(xsr);
		}
	}

	private static void tractarNom(XMLStreamReader reader) {
		if ( reader.getEventType() == XMLStreamConstants.CHARACTERS){
			System.out.println(reader.getText());
		}
	}
}
