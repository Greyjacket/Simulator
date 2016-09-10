package version1;

public class NodeFactory {
	//singleton factory
	private static NodeFactory instance = new NodeFactory();
	
	private NodeFactory(){};
	
	public static NodeFactory getInstance(){
		return instance;
	}
	   
	public Pusher createPusher() {
		return new Pusher();
	}

}
