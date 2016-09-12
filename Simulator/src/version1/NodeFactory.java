package version1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NodeFactory {
	//singleton factory
	
	ArrayList<String> bandNames;
	
	private static NodeFactory instance = new NodeFactory();
	
	private NodeFactory(){};
	
	public static NodeFactory getInstance(){
		return instance;
	}
	   
	public Pusher createPusher() {
		return new Pusher();
	}
	
	public void readBandNames() throws IOException{
		FileArrayProvider fap = new FileArrayProvider();
		this.bandNames = (ArrayList<String>) fap.readLines("Bandnames.txt");		
	}
	
	public String getRandomBandName(){
			
		if (this.bandNames != null){
			Random rn = new Random();
			int randomIndex = rn.nextInt(this.bandNames.size());
			
			if(this.bandNames.get(randomIndex) != null){
				String randomBandName = this.bandNames.get(randomIndex);
				this.bandNames.remove(randomIndex);
				return randomBandName;
			}
		}
		return null;
	}	
}
