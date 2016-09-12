package version1;

import java.util.HashSet;

public class TierOneRelay extends Relay {
	
	public String location; 
	
	public TierOneRelay(int id, String name){
		this.id = id;
		this.name = name;
		this.connections = new HashSet<Node>();
	}
	
	public void update(){}

	@Override
	public void link(Node node) {
		// TODO Auto-generated method stub
		
	};
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;		
	}
	
	public void setLocation(String location){
		this.location = location;
	}
}
