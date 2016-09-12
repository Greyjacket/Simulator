package version1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RelayFactory {
	
	private List<String> locations;
	
	//singleton factory
	private static RelayFactory instance = new RelayFactory();
		
	private RelayFactory(){};
	
	public static RelayFactory getInstance(){
		return instance;
	}
	   
	public Relay createTierOneRelay(int id, String name, String location) {
		return new TierOneRelay(id, name);
	}
	
	public String getRandomLocation() throws NullPointerException{
		
		Random rn = new Random();
		
		try{
			return locations.get(rn.nextInt(locations.size()));
		}
		catch(NullPointerException exception){
			return "Buffalo";
		}
	}
	
	public void loadLocations(){
		Connection c = null;
	    Statement stmt = null;
	    locations = new ArrayList<String>();
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "SELECT Location FROM Census;"; 
	      ResultSet rs = stmt.executeQuery(sql);

	      while ( rs.next() ) {	          
	          String  name = rs.getString("Location");
	          locations.add(name);	          
	      }
	      
	      rs.close();
	      stmt.close();
	      c.commit();
	      c.close();
	      System.out.println("RelayFactory loaded locations successfully.");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records created successfully");
	}
}
