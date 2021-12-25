package testproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.fasterxml.jackson.annotation.PropertyAccessor;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class PostgresServer extends AbstractVerticle{
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
	    Connection c = null;
	      try {
	    	  String posgrehost=System.getProperty("POSTGRE_HOST", "postgre");
	    	  String posgreport=System.getProperty("POSTGRE_PORT", "5432");
	    	  System.out.println("posgreurl " + System.getProperty("POSTGRE_HOST"));
	    	  System.out.println("posgreport " + System.getProperty("POSTGRE_PORT"));
	    	  
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://" + posgrehost + ":" + posgreport +"/world",
	            "postgres", "docker");
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         startPromise.fail("Failed to connect ");
	      }
	     Statement stmt = c.createStatement();
	      ResultSet set  = stmt.executeQuery("SELECT * from products");
	      if (set != null){
	    	  while ( set.next() ) {
	              int id = set.getInt("id");
	              String  name = set.getString("name");

	              System.out.println( "ID = " + id );
	              System.out.println( "NAME = " + name );

	              System.out.println();
	           }
	    	  set.close();
	           stmt.close();
	      }
	      System.out.println("Opened database successfully");
	      startPromise.complete();
	}
}
