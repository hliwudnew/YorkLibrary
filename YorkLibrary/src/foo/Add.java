package foo;

public class Add implements ICartCommand1 {
	private Cart cart;

	public Add(Cart cart) {
		this.cart=cart;
	}

	@Override
	public void execute(Item item) {
		// TODO Auto-generated method stub
		cart.add(item);
	}
	
}
