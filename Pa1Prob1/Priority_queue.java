import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/***********************************************************
 * Priority_queue class
 * @author Benlemma
 ***********************************************************/
public class Priority_queue {
	static Customer[] customers = new Customer[100000];
	
	/***********************************************************
	 * Main function: a drivaer function that reads file 
	 *                then sort the array in min-heap sort; 
	 *                finaly return the name of each customers. 
	 * @param args
	 ***********************************************************/
	public static void main(String[] args) {
		
		int count = 0;
		String name;
		int priority;
		String pass ;
		
		/********* read data from txt file. *******/
		try {
			Scanner input=  new Scanner(new File(args[0]));
			while(input.hasNext())
			{
				name = input.next(); // store the name
				pass = input.next(); // pass the collen
				priority = input.nextInt();	// store the priority
				Customer customer = new Customer(name, priority); //creat object of customer
			    customers[count] = customer; //add the object to the array.
			    count++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file \""+args[0]+"\" found!");
		}
		heapSort(customers); //sort the array
		
		/************  write the data to txt file  ************/
		try {
			PrintWriter output = new PrintWriter(new File("output.txt"));
			String str = "";
			for (int i=0; i < (customers.length - 1); i++){
				if(customers[i] != null){
					str += customers[i].getName();
					str += " ";
				}
			}
			if (customers[customers.length - 1] != null){
				str += customers[customers.length - 1].getName();
			}
			output.print(str);
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!");
		}
		System.out.println("output.txt created!");
	}
	
	/***********************************************************
	 * heapSort function: sorts array. 
	 * @param c(customer list)
	 ***********************************************************/
	public static void heapSort (Customer[] c){
		int s = c.length;
		for (int i = ((s/2)); i>=0; i--){
			heapify(c, i, s-1);
		}
		for (int i = s-1; i>0; i--){
			Customer c1 = c[0];
			c[0] = c[i];
			c[i] = c1;
			heapify(c, 0, i-1);
			
		}
	}

	/***********************************************************
	 * heapSort function: helper function to sort the array
	 * @param c(customer list)
	 * @param x(parent node)
	 * @param z(child node)
	 ***********************************************************/
	public static void heapify(Customer[] c, int x, int z){
		int y;
		Customer t;
		
		while (2*x <= z){
			if (x*2 == z){
				y = x*2;
			}
			else if (c[x*2] != null && c[x*2+1] != null && c[x*2].getPriority() < c[x*2 + 1].getPriority()){
				y = x*2;
			}
			else 
				y = x*2+1;
			
			if (c[x] != null && c[y] != null && c[x].getPriority() > c[y].getPriority()){
				t = c[x];
				c[x] = c[y];
				c[y] = t;
				x = y;
			}
			else 
				break;

		}
		
	}
	
	/***********************************************************
	 * Insert function: inserts an element to the array
	 * @param S(customer list)
	 * @param x(single element)
	 ***********************************************************/
	public static void Insert(Customer[] S, Customer x){
		int sz = S.length;
		S[sz] = x;
		heapSort(S);
	}
	/***********************************************************
	 * Maximum function: returns the maximum element
	 * @param S(customer list)
	 ***********************************************************/
	public static String Maximum(Customer[] S){
		heapSort(S);
		return S[0].getName();
	}
	
	/***********************************************************
	 * Extract_Max function: returns the max element and remove 
	 * 						 it from the array.
	 * @param S(customer list)
	 ***********************************************************/
	public static String Extract_Max(Customer[] S){
		heapSort(S);
		String str = S[0].getName();
		for (int i = 0; i < S.length-1; i++){
			S[i] = S[i+1];
		}
		S[S.length-1] = null;
		return str;
	}
}
