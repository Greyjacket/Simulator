package version1;

import java.util.Collection;

public class Pusher extends User implements Consumable{
	
	int pushes;
	boolean isPushing;
			
	public Pusher(int id, String name, int pushes, Collection<Node> adjacencyList){
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
