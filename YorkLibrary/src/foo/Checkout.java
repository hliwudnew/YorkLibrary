package foo;

public class Checkout implements ICartCommand2 {
	private Cart cart;
	
	public Checkout(Cart cart) {
		this.cart=cart;
	}
	@Override
	public boolean execute() {
		return cart.checkout();
	}
}
