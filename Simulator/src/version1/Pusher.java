package version1;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Pusher extends User implements Consumable {
	
	short pushes;
	boolean isPushing;
			
	public Pusher(String name, int id, short pushes, ArrayList<Node> adjacencyList){
		this.id = id;
		this.name = name;
		this.pushes = pushes;
		this.connections = adjacencyList;
	}	
	
	
	/*public link(Node node){
		this
	}*/
	public void scan(){
		Iterator itr = connections.iterator();
		
		while(itr.hasNext()){
			
		}
		/*for (int i = 0; i < connections.size(); i++) {
			if connections[i].isPushing = true;			
		}*/
	}
	
	//@Override
	public void consume(Pusher otherPusher){		
		this.pushes++;
		otherPusher.pushes--;
	}


}
