package foo;

public class PhysicalItem extends Item{

	private String dueDate;
	private User borrower;
	private boolean disabled;
	private double fee;
	
	
	public PhysicalItem() {
		
	}
	
	public PhysicalItem(String dueDate,User borrower,boolean disabled,double fee) {
		this.dueDate = dueDate;
		this.borrower = borrower;
		this.disabled = disabled;
		this.fee = fee;
	}
	
	//Setters
	public void setDueDate(String date) {
		this.dueDate = date;
	}
	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	
	//Getters
	public String getDueDate() {
		return this.dueDate;
	}
	
	public User getBorrower() {
		return this.borrower;
	}
	
	public boolean getDisabled() {
		return this.disabled;
	}
	
	public double getFee() {
		return this.fee;
	}
}
