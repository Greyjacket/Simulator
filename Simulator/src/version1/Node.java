package version1;

import java.util.Collection;

public abstract class Node {
	public String name;
	int id;
	protected Collection<Node> connections;
	
	public abstract void link(Node node);
	
	public abstract void setId(int id);
	
	public abstract void setName(String name);
}
