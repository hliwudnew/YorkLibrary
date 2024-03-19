package foo;

import java.util.Date;

public class PhysicalItem extends Item{

	private Date dueDate;
	private String borrower;
	private double fee;
	
	
	public PhysicalItem() {
		
	}
	
	public PhysicalItem(Date dueDate,String borrower,double fee) {
		this.dueDate = dueDate;
		this.borrower = borrower;
		this.fee = fee;
	}
	
	public double calculateFee() {
		double amountOwed=0;
		int count=0; //keeps track of how many days the current time is past due date 
		long dueDate=this.getDueDate().getTime();
		long thisDate=new Date().getTime();
		//while the difference between current time of return and due date is greater than 
		//a day, keep adding 1 to count to count number of days past due date
		while((thisDate-dueDate)>=86400000L) {
			count++;
			thisDate-=86400000; //keep subtracting a day off curent date until we are at dueDate
		}
		amountOwed = count * 0.50; //for each day, add 50 cents to amount owed
		this.setFee(amountOwed);
		return amountOwed;
	}
	//Setters
	public void setDueDate(Date date) {
		this.dueDate = date;
	}
	public void setBorrower(String values) {
		this.borrower = values;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	
	//Getters
	public Date getDueDate() {
		return this.dueDate;
	}
	
	public String getDueStatus() {
		
		Date current = new Date();
		long difference = this.dueDate.getTime() - current.getTime();
		
		if (dueDate.before(current)) {
			return "Overdue";
		} else if (difference <= 86400000L) {
			return "Due in: " + difference / 3600000 + " Hours";
		} else {
			return "Due in >24 Hours";
		}
			
	}
	
	public String getBorrower() {
		return this.borrower;
	}
	
	public double getFee() {
		return this.fee;
	}
	
	
}
