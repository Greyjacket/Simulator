package version1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class NodeFactory {
	//singleton factory
	
	ArrayList<String> bandNames;
	
	private static NodeFactory instance = new NodeFactory();
	
	private NodeFactory(){};
	
	public static NodeFactory getInstance(){
		return instance;
	}
	   
	public Pusher createBlankPusher() {
		return new Pusher();
	}
	
	//return ArrayList of Dummies (max 10000)
	public ArrayList<Pusher> createDummyPushers(int numberOfPushers){
		
		//10000 max
		if (numberOfPushers > 10000){
			numberOfPushers = 10000;
		}
		
		Connection c = null;
	    Statement stmt = null;
	    ArrayList<Pusher> pushers = new ArrayList<Pusher>();
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "SELECT Name, City, Canton, Rating FROM Pushers;"; 
	      ResultSet rs = stmt.executeQuery(sql);

	      Pusher pusher;
	      int createdPushers = 0;
	      
	      while (numberOfPushers-- > 0) {
	    	  pusher = this.createBlankPusher();
	          pusher.setName(rs.getString("Name"));
	          pusher.setCity(rs.getString("City"));
	          pusher.setCanton(rs.getString("Canton"));
	          pusher.setRating(rs.getInt("Rating"));
	          pusher.setLevel(1);
	          pusher.setExperience(0);
	          pushers.add(pusher);
	          createdPushers++;
	          rs.next();
	      }
	      
	      rs.close();
	      stmt.close();
	      c.commit();
	      c.close();
	      System.out.println("Created " + createdPushers +" pushers");
	      return pushers;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	      return null;
	    }
	}
	
	//get bandnames from bandname table in db
	public void readBandNames(){
		
		Connection c = null;
	    Statement stmt = null;
	    bandNames = new ArrayList<String>();
	    
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "SELECT Names FROM Bandnames;"; 
	      ResultSet rs = stmt.executeQuery(sql);

	      while ( rs.next() ) {	          
	          String  name = rs.getString("Names");
	          bandNames.add(name);	          
	      }
	      
	      rs.close();
	      stmt.close();
	      c.commit();
	      c.close();
	      System.out.println("NodeFactory loaded bandnames successfully.");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	
	public String getRandomBandName(){
			
		if (this.bandNames != null){
			Random rn = new Random();
			int randomIndex = rn.nextInt(this.bandNames.size());
			
			if(this.bandNames.get(randomIndex) != null){
				String randomBandName = this.bandNames.get(randomIndex);
				this.bandNames.remove(randomIndex);
				return randomBandName;
			}
		}
		return null;
	}

	//generate an array of random likabilities, normally distributed
	public int[] generateNormalRatings(int numberOfUsers){
		
		Random rn = new Random();
		int tests = numberOfUsers;
		int nd[] = new int[numberOfUsers];
		int pos = 0;
		while(tests-- != 0){
			
			double rating = rn.nextGaussian() * (50/3) + 50; //mean of 50, standard dev of 3
			if(rating < 0){
				rating = 1;
			}
			if(rating > 100){
				rating = 99;
			}
			
			nd[pos++] = (int) rating;
		}
		return nd;
	}
	
	//10,000 dummy users generated, rated, and inserted in sqlite db here.
	public void generateUsers(){
		
		Connection c = null;
	    Statement stmt = null;
	    this.readBandNames();
	    int[] ratings = this.generateNormalRatings(10000);
	    
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");
		      	
		      this.formatCensusData();
		      stmt = c.createStatement();
		      String sql = "SELECT City, State, Estimated FROM Census;"; 
		      ResultSet rs = stmt.executeQuery(sql);

		      int average = 8770;
		      int localPopulation = 0;
		      int totalNumberOfUsers = 10000;
		      String city;
		      String canton;
		      
		      while ( rs.next() ) {	          
		          
		    	  localPopulation = rs.getInt("Estimated");		    	  
		    	  int counter = 0;
		    	  String name = "";
		    	  while(counter <= localPopulation && totalNumberOfUsers > 0){
		    		  name = this.getRandomBandName();
		    		  System.out.println(name);
		    		  city = rs.getString("City");
		    		  canton = rs.getString("State");
		    		  stmt = c.createStatement();
		    		  name = name.replaceAll("'","''");
		    		  sql = "INSERT INTO Pushers (Name, City, Canton, Rating) VALUES ('"+name+"', '"+ city +"', '" + canton + "'," + ratings[totalNumberOfUsers -1] + ");"; 
		    		  stmt.execute(sql);
		    		  counter = counter + average;
		    		  totalNumberOfUsers--;		    		  
		    	  }		                    
		      }

		      rs.close();
		      stmt.close();
		      c.commit();
		      c.close();
		      System.out.println("NodeFactory loaded bandnames successfully.");
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		
	}

	//format any anomalies here ex: "Boise" was Boise[Boise[Boise[Boise" for some reason
	public void formatCensusData(){
		
		Connection c = null;
	    Statement stmt = null;
	    
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      String sql = "SELECT City FROM Census;"; 
		      ResultSet rs = stmt.executeQuery(sql);

		      int numberOfRecords = 0;
		      String test;
		      
		      while ( rs.next() ) {	          
		    	  test = rs.getString("City");

		    	  if(test.contains("Boise") == true){
		    		  sql = "UPDATE Census SET City='Boise' WHERE City='"+ rs.getString("City")+"'";
		    		  stmt = c.createStatement();
		    		  stmt.execute(sql);		    		  
		    		  numberOfRecords++;
		    	  }
		      }
		      
		      int out = numberOfRecords;
		      System.out.println(out);
		      rs.close();
		      stmt.close();
		      c.commit();
		      c.close();
		      System.out.println("NodeFactory loaded bandnames successfully.");
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		
	}
}
