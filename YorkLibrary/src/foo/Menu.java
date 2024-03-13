package foo;

public class Menu {
	private ICartCommand1 addCommand;
	private ICartCommand1 removeCommand;
	private ICartCommand2 clearCommand;
	private ICartCommand2 checkoutCommand;
	
	public Menu(ICartCommand1 addCommand,
			ICartCommand1 removeCommand,
			ICartCommand2 clearCommand,
			ICartCommand2 checkoutCommand) {
		this.addCommand=addCommand;
		this.removeCommand=removeCommand;
		this.clearCommand=clearCommand;
		this.checkoutCommand=checkoutCommand;
	}
	public void clickAdd(Item item) {
		addCommand.execute(item);
	}
	public void clickRemove(Item item) {
		removeCommand.execute(item);
	}
	public double clickClear() {
		return clearCommand.execute();
	}
	public double clickCheckout() {
		return checkoutCommand.execute();
	}
	
}