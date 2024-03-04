package foo;

public class PhysicalItem extends Item{

	private String dueDate;
	private String borrower;
	private double fee;
	
	
	public PhysicalItem() {
		
	}
	
	public PhysicalItem(String dueDate,String borrower,double fee) {
		this.dueDate = dueDate;
		this.borrower = borrower;
		this.fee = fee;
	}

	//Setters
	public void setDueDate(String date) {
		this.dueDate = date;
	}
	public void setBorrower(String values) {
		this.borrower = values;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	
	//Getters
	public String getDueDate() {
		return this.dueDate;
	}
	
	public String getBorrower() {
		return this.borrower;
	}
	
	public double getFee() {
		return this.fee;
	}
	
	
}
