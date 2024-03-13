package foo;

public class Clear implements ICartCommand2 {
	private Cart cart;

	public Clear(Cart cart) {
		this.cart=cart;
	}
	@Override
	public double execute() {
		return cart.clear();
	}
}
