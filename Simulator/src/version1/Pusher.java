package version1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Pusher extends User implements Consumable, Ratable{
	
	boolean isPushing;
	private int pushes;			//no longer based on level. running average? or half the amount of pushes lost within a recent period of time	
	public int likes;
	private int dislikes;
	private int experience;
	public int level;
	public String location;
	public Token[] tokens;
	  
	public Pusher(){
		this.connections = new HashSet<Node>();
		this.setExperience(0);
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
					this.rate(otherPusher);
					this.link(otherPusher);
				}
			}
		}
	}
	
	public void rate(Pusher otherPusher){
		otherPusher.likes++;
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

	//---------------------------------------------------------------------------------Setters
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;		
	}
	
	public void setNumberOfPushes(int pushes){
		
		this.pushes = pushes;
		
		if(pushes > 0){
			this.isPushing = true;
		}
		else{
			this.isPushing = false;
		}
	}
	
	public void setNumberOfLikes(int likes){		
		this.likes = likes;
	}
	
	public void setNumberOfDislikes(int dislikes){
		this.dislikes = dislikes;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void setExperience(int experience){
		this.experience = experience;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	public int getNumberOfPushes(){
		return pushes;
	}
	
}
