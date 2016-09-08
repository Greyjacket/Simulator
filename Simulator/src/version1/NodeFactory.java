package version1;

public class NodeFactory {
	//singleton factory
	private static NodeFactory instance = new NodeFactory();
	
	private NodeFactory(){};
	
	public static NodeFactory getInstance(){
		return instance;
	}
	   
	public Pusher createPusher(int id, String name, int pushes) {
		return new Pusher(id, name, pushes);
	}

}
