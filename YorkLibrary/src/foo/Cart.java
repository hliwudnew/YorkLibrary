package foo;
import java.util.ArrayList;

//Cart class - The class where each user has exactly one cart that will hold the items they add to it,
//the logic for the operations on it are held here, while the user interacts with it through the Menu (Invoker) class (see command pattern)
public class Cart {
	private ArrayList<Item> itemsInCart;
	private User owner;
	double initialPrice;
	private String currency = "CAD"; //used to find what currency user wants to pay with, used for getting payment info in checkout, CAD is default
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
			//check if item can actually be added to cart
			if(item instanceof PhysicalItem) {
				if(((PhysicalItem)item).getBorrower().equals(this.owner.getEmail())) {
					System.out.println("Item already being rented!");
					return;
				}
			}
			itemsInCart.add(item);
			this.initialPrice+=item.getPrice();
		}
		//purely for testing purposes, can remove print statement
		else {
			System.out.println("Already in cart!");
		}
	}
	public void remove(Item item) {
		if(itemsInCart.contains(item)) {
			itemsInCart.remove(item);
			this.initialPrice-=item.getPrice();
		}
		//purely for testing purposes, can remove print statement
		else {
			System.out.println("Item not in cart!");
		}
	}
	public double clear() {
		itemsInCart.clear();
		return 0.0; //returns new price of 0
	}
	
	//checkout method will check if user can checkout item and then return a message:
	//positive double value if the checkout is possible, showing the price

	public double checkout() {
		double finalPrice=getConvertedPrice();
		//check if checking out all items in cart would cause user to exceed physical item limit
		//only count physical items as online items have no limit

		//if the user will not exceed limit, then begin checking out all items
		//if the item is physicalitem, then rent it
		//if item is a onlineitem like newsletter, subscribe to it
		if(canCheckout()>0) {
			for(Item item: this.itemsInCart) {
				if(item instanceof PhysicalItem) {
					owner.rentPhysicalItem((PhysicalItem)item);
				}
				else {
					owner.subscribe((OnlineItem)item);
				}
			}
		}
			//this.clear();
		return finalPrice; //returns price of checked out cart to be displayed
	}
	public void setCurrency(String currency) {
		this.currency=currency;
	}
	//method to check if user is allowed to checkout currently
	//-1 if user is trying to rent too many physical items
	//-2 if cart is empty
	//1 if allowed
	public int canCheckout() {
		if(itemsInCart.size()<1) {
			return -2;
		}
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
			return -1;
		}
		return 1;
	}
	public double getInitialPrice() {
		return this.initialPrice;
	}
	public double getConvertedPrice() {
		IPayment payment = new CurrencyExchange(new Payment(this.initialPrice));
		double finalPrice = payment.getPrice(this.currency);
		return finalPrice;
	}
}
