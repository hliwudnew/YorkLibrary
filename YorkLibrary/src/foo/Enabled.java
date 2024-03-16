package foo;

public class Enabled implements ItemState{

	@Override
	public void status(ItemStateContext item) {
		item.setState(new Enabled());
		System.out.println("Item now Enabled");
	}
	
}
