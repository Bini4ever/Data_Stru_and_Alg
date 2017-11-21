/***********************************************************
 * Customer class
 * @author Benlemma
 * This class holds the customer's name and priority.
 ***********************************************************/
public class Customer {

	private String name;
	private int priority;
	
	/*********************************************************
	 * Contractor
	 * @param name
	 * @param priority
	 *********************************************************/
	public Customer(String name, int priority) {
		super();
		this.name = name;
		this.priority = priority;
	}
	
	/**********************************************************
	 * getName method 
	 * @return the name of the customer
	 ***********************************************************/
	public String getName() {
		return name;
	}
	
	/**********************************************************
	 * setName method: initializes the name of the customer
	 * @parm name
	 ***********************************************************/
	public void setName(String name) {
		this.name = name;
	}
	
	/**********************************************************
	 * getPriority method 
	 * @return the priority of the customer
	 ***********************************************************/
	public int getPriority() {
		return priority;
	}
	
	/**********************************************************
	 * setPriority method: initializes the priority of the customer
	 * @parm priority
	 ***********************************************************/
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override //to string metod to return the elements of the customer class.
	public String toString() {
		return name + " : " + priority;
	}
	
}
