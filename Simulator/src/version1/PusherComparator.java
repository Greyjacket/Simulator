package version1;

import java.util.Comparator;
import java.util.Random;

public class PusherComparator implements Comparator<Pusher>{
	
	@Override 
	public int compare(Pusher entry, Pusher otherPusher) {
		
		int diff = (entry.level - otherPusher.level);
		double bias = 2;
		double modifier = 50 + (diff * bias);
		Random rn = new Random();
		double roll = rn.nextDouble() * 100;
		
		//defender wins on a tie
		if(roll >= modifier){
			return -1;
		}
		else{
			return 1;
		}		
	}
}
