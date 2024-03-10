package foo;

public class Checkout implements ICartCommand2 {
	private Cart cart;
	
	public Checkout(Cart cart) {
		this.cart=cart;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		cart.checkout();
	}
}
