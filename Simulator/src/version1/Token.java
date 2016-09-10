package version1;

public class Token {

	public boolean isPushed;
	public int trackId;
	public int durationInSeconds;
	public int likes;
	public int dislikes;
	public String title;
	
	public Token(int trackId, String title, int duration){
		this.trackId = trackId;
		this.title = title;
		this.durationInSeconds = duration;	
		this.likes = 0;
		this.dislikes = 0;
	}
	
	public void setLikes(int likes){
		this.likes = likes;
	}
	
	public void setDislikes(int dislikes){
		this.dislikes = dislikes;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	
}
