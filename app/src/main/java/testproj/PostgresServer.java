package testproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class PostgresServer extends AbstractVerticle{
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
	    Connection c = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/world",
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
