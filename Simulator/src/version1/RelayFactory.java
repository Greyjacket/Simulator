package version1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RelayFactory {
	
	public List<String> locations;
	
	//singleton factory
	private static RelayFactory instance = new RelayFactory();
		
	private RelayFactory(){};
	
	public static RelayFactory getInstance(){
		return instance;
	}
	   
	public TierOneRelay createTierOneRelay() {
		return new TierOneRelay();
	}
	
	public ArrayList<TierOneRelay> createDummyRelays(){
		ArrayList<TierOneRelay> relays = new ArrayList<TierOneRelay>();
		
		this.loadLocations();
		
		int numberOfCreatedRelays = 0;
		for(String location : this.locations){
			TierOneRelay relay = this.createTierOneRelay();
			relay.setName(location);
			relay.setLocation(location);
			relays.add(relay);
			numberOfCreatedRelays++;
		}
		
		System.out.println("Created " + numberOfCreatedRelays +" relays");
		return relays;
	}
	
	//get a random location
	public String getRandomLocation() throws NullPointerException{
		
		Random rn = new Random();
		
		try{
			return locations.get(rn.nextInt(locations.size()));
		}
		catch(NullPointerException exception){
			System.out.println("Null pointer in getRandomLocation(). Assigning 'Buffalo'");
			return "Buffalo";
		}
	}
	
	//load locations from Census
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
	      String sql = "SELECT City FROM Census;"; 
	      ResultSet rs = stmt.executeQuery(sql);

	      while ( rs.next() ) {	          
	          String  name = rs.getString("City");
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
