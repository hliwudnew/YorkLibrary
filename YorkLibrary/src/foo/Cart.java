package foo;
import java.util.ArrayList;

//Cart class - The class where each user has exactly one cart that will hold the items they add to it,
//the logic for the operations on it are held here, while the user interacts with it through the Menu (Invoker) class (see command pattern)
public class Cart {
	private ArrayList<Item> itemsInCart;
	private User owner;
	public Cart(ArrayList<Item> userItems, User owner) {
		this.itemsInCart=userItems;
		this.owner=owner;
	}
	public ArrayList<Item> getItems(){
		return itemsInCart;
	}
	public void add(Item item) {
		//add item to cart if not already in cart
		if(!(itemsInCart.contains(item))) {
			itemsInCart.add(item);
		}
		//purely for testing purposes, can remove print statement
		else {
			System.out.println("Already in cart!");
		}
	}
	public void remove(Item item) {
		itemsInCart.remove(item);
	}
	public void clear() {
		itemsInCart.clear();
	}
	public void checkout() {
		//check if checking out all items in cart would cause user to exceed physical item limit
		//only count physical items as online items have no limit
		int count1=0;
		int count2=0;
		for(Item item: owner.getRented()) {
			if(item instanceof PhysicalItem) {
				count1++;
			}
		}
		for(Item item: this.itemsInCart) {
			if(item instanceof PhysicalItem) {
				count2++;
			}
		}
		if(count1+count2>10) {
			System.out.println("Cannot exceed 10 rented limit for items! Return more items or remove them from cart to checkout.");
		}
		//if the user will not exceed limit, then begin checking out all items
		//if the item is physicalitem, then rent it
		//if item is a onlineitem like newsletter, subscribe to it
		else {
			for(Item item: this.itemsInCart) {
				if(item instanceof PhysicalItem) {
					owner.rentPhysicalItem((PhysicalItem)item);
				}
				else {
					owner.subscribe((OnlineItem)item);
				}
			}
			itemsInCart.clear();
		}
	}
}
