package version1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Pusher extends User implements Consumable{
	
	int pushes;
	boolean isPushing;
			
	public Pusher(int id, String name, int pushes){
		this.id = id;
		this.name = name;
		this.pushes = pushes;
		this.connections = new HashSet<Node>();
		if(pushes <= 0){
			this.isPushing = false;
		}
		else{
			this.isPushing = true;
		}
	}	
	
	public void relayScan(Relay relay){
		
		//to Do: convert this to a stream for speed
		
		/*relay.getClass();		
		relay.connections.stream()
		.filter(t -> t.getClass().equals(Type Pusher);*/
		
		Node[] nodes = relay.connections.toArray(new Node[relay.connections.size()]);
		ArrayList<Pusher> pushers = new ArrayList<Pusher>();
		Random rn = new Random();
		
		for(int i = 0; i < nodes.length; i++){
			if (nodes[i] instanceof Pusher){
				Pusher pusher = (Pusher) nodes[i];
				if(pusher.isPushing){
					pushers.add(pusher);
				}				
			}
		}
		
		this.consume(pushers.get(rn.nextInt(pushers.size())));
		
	}
	
	public void selfScan(){			
		
		for (Node otherNode : connections) {
			
			if(otherNode instanceof Pusher){
				
				Pusher otherPusher = (Pusher) otherNode;
				
				if(otherPusher.isPushing){
					this.consume(otherPusher);
				}
			}
		}
	}

	public void consume(Pusher otherPusher){		
		
		if(this.pushes++ == 0){
			this.isPushing = true;
		}
		
		if(--otherPusher.pushes == 0){
			otherPusher.isPushing = false;
		}
	}
	
	public void link(Node otherNode){
		
		connections.add(otherNode);	
		
		if(otherNode instanceof Relay){
			//establish mutual link with Relay
			otherNode.connections.add(this);			
		}
		
	}

}
