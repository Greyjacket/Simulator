package version1;

import java.util.HashSet;

public class TierOneRelay extends Relay {

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
}
