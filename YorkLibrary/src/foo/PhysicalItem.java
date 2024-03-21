package foo;

import java.util.Date;

public class PhysicalItem extends Item implements PhysicalItemPrototype{

	private Date dueDate;
	private String borrower;
	private double fee;
	private boolean lost;
	private static int nextValidId=0;

	//TODO add a static id variable to ITEM class that gets incremented by 1 on object creation
	//TODO add a clone method and implement from prototype interface so that phys items can be cloned to have 20 copies
	

	public PhysicalItem() {
		this.nextValidId++;
	}
	
	public PhysicalItem(Date dueDate,String borrower,double fee) {
		this.dueDate = dueDate;
		this.borrower = borrower;
		this.fee = fee;
		this.lost=false;
		this.nextValidId++;
	}
	//make a physical item with all required fields (using this for cloning)
	public PhysicalItem(int id, String name, double price, ItemStateContext status, Date dueDate, String borrower,double fee, double discount) {
		super(id, name, price,status,discount);
		this.dueDate = dueDate;
		this.borrower = borrower;
		this.fee = fee;
		this.lost=false;
		this.nextValidId++;
	}
	
	
	//this method will clone the item it is being called on
	//a cloned item should copy it's fields such as name, price, discount and status as all items of this type should share these
	//and since all other fields such as duedate, id, lost, fee and borrower are UNIQUE, they should be different from the original
	// (i.e a copy is a new item and thus it is impossible for it to have a borrower, be lost, incur a fee, etc. upon creation)
	// NOTE: this method deep copies all non-primitive fields
	@Override
	public PhysicalItem cloneItem() {
		String cloneName = new String(this.getName());
		ItemStateContext cloneStatus=null;
		if(this.getStatus().getState()==null) {
			cloneStatus=null;
		}
		else if(this.getStatus().getState() instanceof Disabled) {
			cloneStatus= new ItemStateContext(new Disabled());
		}

		else if(this.getStatus().getState() instanceof Enabled) {
			cloneStatus= new ItemStateContext(new Enabled());
		}
		
		PhysicalItem clonedItem = new PhysicalItem(PhysicalItem.nextValidId, cloneName, this.getPrice(), cloneStatus, null, "BLANK", 0.0, this.getDiscount());
		return clonedItem;
	}
	
	public static int getNextValidId() {
		return nextValidId;
	}

	public int daysOverdue() {
		int count=0; //keeps track of how many days the current time is past due date 
		long dueDate=this.getDueDate().getTime();
		long thisDate=new Date().getTime();
		//while the difference between current time of return and due date is greater than 
		//a day, keep adding 1 to count to count number of days past due date
		while((thisDate-dueDate)>=86400000L) {
			count++;
			thisDate-=86400000; //keep subtracting a day off curent date until we are at dueDate
		}
		return count;
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
			thisDate-=86400000L; //keep subtracting a day off curent date until we are at dueDate
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

	public void setLost(boolean lost) {
		this.lost = lost;
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
	
	public boolean isLost() {
		return lost;
	}


}
