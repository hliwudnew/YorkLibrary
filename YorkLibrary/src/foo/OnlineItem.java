package foo;

import java.util.ArrayList;

public class OnlineItem extends Item{

	ArrayList<User> subscribers = new ArrayList<User>();
	
	public OnlineItem() {
		
	}
	
	public OnlineItem(ArrayList<User> subs) {
		this.subscribers = subs;
	}
	//Setters
	public void addSubscriber(User person) {
		if(person != null) {
			this.subscribers.add(person);
		}
		else {
			System.out.print("Could not add they are null");
		}
	}
	public void removeSubscriber(User person) {
		this.subscribers.remove(person);
	}
	
	//Getters
	public ArrayList<User> getSubscribers(){
		return this.subscribers;
	}
	public User getSubscriber(User person) {
		ArrayList<User> current = getSubscribers();
		if(current.contains(person)) {
			return person;
		}
		else {
			return null;
		}
	}

}
