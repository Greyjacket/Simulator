package version1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Pusher extends User implements Consumable, Ratable{
	
	boolean isPushing;
	private int pushes;			//no longer based on level. running average? or half the amount of pushes lost within a recent period of time	
	public int likes;
	private int dislikes;
	public int experience;
	public int rating;
	public int level;
	public String city;
	public String canton;
	public Token[] tokens;
	public Collection<TierOneRelay> connectedRelays;
	
	public Pusher(){
		this.connections = new HashSet<Node>();
		this.connectedRelays = new HashSet<TierOneRelay>();
	}
	
    public void scan(int scanType) {
        switch (scanType) {
            case 0:
                this.selfScan();
                break;
                    
            case 1:
                this.localScan();
                break;
                         
            case 2:
            	break;
                        
            default:
                System.out.println("Midweek days are so-so.");
                break;
        }
    }
    
	public boolean localScan(){
		
		//to Do: convert this to a stream for speed
		
		/*relay.getClass();		
		relay.connections.stream()
		.filter(t -> t.getClass().equals(Type Pusher);*/
		for(TierOneRelay relay : connectedRelays){
			
			if (relay.location.equals(this.city)){
				//insert queue error checking here
				Pusher pusher = relay.queue.poll();
				this.rate(pusher);
				this.consume(pusher);
				relay.queue.add(pusher);
			}
		}
		
		/*Node[] nodes = relay.connections.toArray(new Node[relay.connections.size()]);
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
		
		this.consume(pushers.get(rn.nextInt(pushers.size())));*/
		
	}	
	
	public void evaluateExperience(){

		//geometric sequence
		int count = 20; //count levels
		
		while(count-- <= 0){
		
			if(experience >= Math.pow(2, count)){
				level = count;
			}
		}	
		
	}
	
	public void selfScan(){			
		
		for (Node otherNode : connections) {
			
			if(otherNode instanceof Pusher){
				
				Pusher otherPusher = (Pusher) otherNode;
				
				if(otherPusher.isPushing){
					this.consume(otherPusher);
					this.rate(otherPusher);
				}
			}
		}
	}
	
	public void rate(Pusher otherPusher){
		
		Random rn = new Random();
		int rating = rn.nextInt(100);
		
		if(otherPusher.rating <= rating){
			otherPusher.likes++;
			otherPusher.experience++;
			this.link(otherPusher);
		}
		else{
			otherPusher.dislikes++;
		}
	}
	
	public void consume(Pusher otherPusher){		
		
		if(pushes++ == 0){
			isPushing = true;
		}
		
		if(--otherPusher.pushes == 0){
			otherPusher.isPushing = false;
		}
	}
	
	public void link(Node otherNode){
		
		connections.add(otherNode);	
	
	}
	
	public void linktoRelay(TierOneRelay relay){

		relay.queue.add(this);
		connectedRelays.add(relay);
			
	}

	//---------------------------------------------------------------------------------Setters
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;		
	}
	
	public void setRating(int rating){
		this.rating = rating;		
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

	public void setCity(String city){
		this.city = city;
	}
	
	public void setCanton(String canton){
		this.canton = canton;
	}
	
	public int getNumberOfPushes(){
		return pushes;
	}	
	
}
