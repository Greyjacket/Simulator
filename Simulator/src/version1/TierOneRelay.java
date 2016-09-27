package version1;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class TierOneRelay extends Relay {
	
	public String location; 
	protected AbstractQueue<Pusher> queue;
	
	public TierOneRelay(){
		this.connections = new HashSet<Node>();
		this.queue = new PriorityQueue<Pusher>();
	}
	
	public void update(){}

	@Override
	public void link(Node node) {
		// TODO Auto-generated method stub		
	};
	
	public void linkToOtherRelays(Collection<TierOneRelay> otherRelays){
		
		for(TierOneRelay relay : otherRelays){
			if(relay.location != this.location){
				this.connections.add(relay);
			}
		}
	}
	
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
