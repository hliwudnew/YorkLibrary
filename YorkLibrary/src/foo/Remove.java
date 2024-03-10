package foo;

public class Remove implements ICartCommand1 {
	private Cart cart;
	
	public Remove(Cart cart) {
		this.cart=cart;
	}
	@Override
	public void execute(Item item) {
		// TODO Auto-generated method stub
		cart.remove(item);
	}
}
