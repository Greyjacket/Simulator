package version1;

import java.util.ArrayList;
import java.util.Collection;

public class Pusher extends User implements Consumable {
	
	short pushes;
	
	public Pusher(String name, int id, short pushes){
		this.id = id;
		this.name = name;
		this.pushes = pushes;
		this.connections = new ArrayList<Node>();
	}	
	
	
	/*public link(Node node){
		this
	}*/
	
	//@Override
	public void consume(Pusher otherPusher){		
		this.pushes++;
		otherPusher.pushes--;
	}


}
