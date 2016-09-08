package version1;
import java.util.Collection;

public class NodeFactory {
	//singleton factory
	private static NodeFactory instance = new NodeFactory();
	
	private NodeFactory(){};
	
	public static NodeFactory getInstance(){
		return instance;
	}
	   
	public Node createNode(int id, String name, short pushes, Collection<Node> connections) {
		return new Pusher(id, name, pushes, connections);
	}

}
