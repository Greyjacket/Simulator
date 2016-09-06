package version1;

import java.util.Collection;

public abstract class Node {
	public String name;
	public short id;
	private Collection<Node> connections;
	
	abstract void link();
	
}
