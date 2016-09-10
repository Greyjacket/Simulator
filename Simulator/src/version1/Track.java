package version1;

public class Track {

	public boolean isPushed;
	public int trackId;
	public int durationInSeconds;
	public int likes;
	public int dislikes;
	public Pusher maker;
	
	public Track(int trackId, int duration, Pusher maker){
		this.trackId = trackId;
		this.durationInSeconds = duration;
		this.maker = maker;
	}
	
	public void setLikes(int likes){
		this.likes = likes;
	}
	
	public void setDislikes(int dislikes){
		this.dislikes = dislikes;
	}
}
