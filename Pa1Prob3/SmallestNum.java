import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/***********************************************************
* Priority_queue class
* @author Benlemma
***********************************************************/
public class SmallestNum {
	
	/***********************************************************
	 * Main function: a drivaer function that reads file 
	 *                and desplay write the output 
	 *           
	 * @param args
	 ***********************************************************/
	public static void main(String[] args)
	{
		int[] nums = new int[1000000]; //array to store input
		int count = 0; //counter for the index
		int i = 0; //int to store the first int
		
		/********* read data from txt file. *******/
		try {
			Scanner input=  new Scanner(new File(args[0]));
			i = input.nextInt(); //store the first int in i
			while(input.hasNextInt()) //loop until the last int added to the array
			{	
				nums[count] = input.nextInt();
				count++;
			}
			input.close(); //close input scanner
			
		} catch (FileNotFoundException e) {
			System.out.println("No file \""+args[0]+"\" found!");
		}
		
		nums = sort(nums, count); //run the sort function
		
		/************  write the the i index value to txt file  ************/
		try {
			PrintWriter output = new PrintWriter(new File("output.txt"));
			output.print(nums[i-1]);
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!");
		}
		System.out.println("output.txt created!");
	
	
	}
	
	// sort using selection sort
	public static int[] sort(int[] nums, int n){
		
		
		for(int i = 0; i < n-1; i++){
			int p = i;
			for(int j = i+1; j < n; j++){
				if (nums[j] < nums[p]){
					p = j;
				}
			}
			if(p != i){
				int t = nums[p];
				nums[p] = nums[i];
				nums[i] = t;
			}
		}
		return nums;
	
	}

}
