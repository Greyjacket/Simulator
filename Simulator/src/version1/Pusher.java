package version1;

import java.util.HashSet;

public class Pusher extends User implements Consumable {
	
	short pushes;
	boolean isPushing;
			
	public Pusher(String name, int id, short pushes, HashSet<Node> adjacencyList){
		this.id = id;
		this.name = name;
		this.pushes = pushes;
		this.connections = adjacencyList;
	}	
	
	public void scan(){			
		
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
		this.pushes++;
		otherPusher.pushes--;
	}

}
