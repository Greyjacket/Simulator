package version1;

public class RelayFactory {
	
	//singleton factory
	private static RelayFactory instance = new RelayFactory();
		
	private RelayFactory(){};
	
	public static RelayFactory getInstance(){
		return instance;
	}
	   
	public Relay createTierOneRelay(int id, String name) {
		return new TierOneRelay(id, name);
	}
}
