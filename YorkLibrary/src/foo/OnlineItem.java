package foo;

import java.util.ArrayList;

public class OnlineItem extends Item{

	String subscriber;
	String link;
	
	public OnlineItem() {
		
	}
	
	public OnlineItem(String sub, String link) {
		this.subscriber = sub;
		this.link = link;
	}
	// Getters
	public String getSubscriber() {
		return this.subscriber;
	}
	public String getLink() {
		return this.link;
	}
	
	//Setters
	public void setSubscriber(String sub) {
		this.subscriber = sub;
	}
	
	public void setLink(String link) {
		this.link = link;
	}


}
