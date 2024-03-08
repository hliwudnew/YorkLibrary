package foo;

import java.util.ArrayList;

public class OnlineItem extends Item{

	ArrayList<User> subscribers;
	String link;
	
	public OnlineItem() {
		this.subscribers = new ArrayList<User>();
	}
	
	public OnlineItem(ArrayList<User> subs, String link) {
		this.subscribers = subs;
		this.link = link;
	}
	// Getters
	public ArrayList<User> getSubscribers() {
		return this.subscribers;
	}
	public String getLink() {
		return this.link;
	}
	
	
	//Setters
	public void setSubscribers(ArrayList<User> sub) {
		this.subscribers = sub;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void addSubscriber(User user) {
		if(user != null && !this.subscribers.contains(user)) {
			this.subscribers.add(user);
		}
	}

}
