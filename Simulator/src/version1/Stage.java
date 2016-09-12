package version1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Stage {

	public static void main(String[] args) throws IOException {
		NodeFactory nodeFactory = NodeFactory.getInstance();
		
		nodeFactory.readBandNames();
		System.out.println(nodeFactory.getRandomBandName());		
		
}
	
public void testOne() throws IOException{
		
	NodeFactory nodeFactory = NodeFactory.getInstance();
	RelayFactory relayFactory = RelayFactory.getInstance();
	
	int numberOfPushers = 10;
	
	String name = "Pusher ";
	int id = 0;
	int pushes;
	Random rn = new Random();
	
	Relay relay = relayFactory.createTierOneRelay(1, "Osininka");
	ArrayList<Pusher> pushers = new ArrayList<Pusher>();
	
	for(int i = 0; i < numberOfPushers; i++){
		
		pushes = rn.nextInt(5);
		Pusher pusher = nodeFactory.createPusher();
		pusher.link(relay);
		pushers.add(pusher);
	}
	
	int numberOfRounds = 100;
	int index;
	Pusher pusher;
	
	File file = new File("Test.txt");
	file.createNewFile();
	FileWriter writer = new FileWriter(file);
	
	while(numberOfRounds-- != 0){
		index = rn.nextInt(pushers.size());
		pusher = pushers.get(index);
		pusher.relayScan(relay);
		
		for (Pusher tester : pushers) {
			//String line = Integer.toString(tester.id) + " " + tester.name + " " + Integer.toString(tester.pushes) + "\n";
			String line = Integer.toString(tester.getNumberOfPushes()) + " ";
			writer.write(line);
		}
		writer.write("\n");
	}
	writer.close();
		
	}
}
