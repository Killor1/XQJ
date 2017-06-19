package introducction;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class Insert {
	public static void main(String [] args) throws XQException{
		XQDataSource xqs = new ExistXQDataSource();
		xqs.setProperty("serverName", "localhost");
		xqs.setProperty("port", "8080");
		
		XQConnection conn= xqs.getConnection("admin", "admin");
		XQExpression xqe = conn.createExpression();
		String cad1 = "update insert \n"+
				"<country car_code='$$'><name>STK Republic</name></country> \n"+
				"preceding doc('/db/apps/demo/data/mondial.xml')/mondial/country[1]";
		xqe.executeCommand(cad1);
		System.out.println("Instruccion executada!!!! ");

	}
}
