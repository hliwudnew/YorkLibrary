package foo;

public class ItemStateContext {
	private ItemState curState;
	
	public ItemStateContext() {
		curState = new Disabled();
	}
	
	public ItemStateContext(ItemState status) {
		curState = status;
	}
	
	public void setState(ItemState state) {
		curState = state;
	}
	
	public ItemState getState() {
		return this.curState;
	}
	
	public void status() {
		curState.status(this);
	}
}
