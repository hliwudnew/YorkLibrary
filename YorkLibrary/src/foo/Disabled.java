package foo;

public class Disabled implements ItemState{

	@Override
	public void status(ItemStateContext item) {
		item.setState(new Disabled());
		System.out.println("Item now Disabled");

	}

}
