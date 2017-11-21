import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/***********************************************************
* BucketSort class
* @author Benlemma
* This class use bucket sort to sort a list of strings
***********************************************************/


/**
 * @author biniamlemma
 *
 */
public class BucketSort {
	public static String[] strs = new String[100000];
	
	/***********************************************************
	 * Main function: a driver function that reads the file 
	 *                and write sorted file to txt file.
	 * @param args
	 ***********************************************************/
	public static void main (String[] args){
		int count = 0;
		//read file
		try {
			Scanner input=  new Scanner(new File(args[0]));
			while(input.hasNext())
			{
				strs[count] = input.next().toLowerCase();
				count++;
			}
			input.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("No file \""+args[0]+"\" found!");
		}
		
		//sort file
		String [][] sortedbucket = sort(count);
		
		// write file to  text file
		try {
			PrintWriter output = new PrintWriter(new File("output.txt"));
			int i=0, j=0;
			for(i=0; i < 26; i++){
					for(j=0; j < 10; j++){
						if (sortedbucket[i][j] == null)
						{ 
							continue;
						} else {
							output.print(sortedbucket[i][j] + " ");	
						}
					}
			}
		output.close();
		}catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!");
		}
		System.out.println("output.txt created!");
	}
	
	
	/************************************************************
	 * sort function: this function uses bucket sort and 
	 *                to sort a list of names.
	 * @param size
	 * @return
	 ***********************************************************/
	public static String[][] sort (int size){
		//buckets to store the stings
		String [][] bucket = new String[26][10];       
		//initialize the buckets
	    for (int i = 0; i < 26; i++){
	    	for (int j = 0; j < 10; j++){
	    		bucket[i][j] = null;
	    	}	
	    }
	    
	    //put string into buckets according to the first caractor.
	    for (int i = 0; i < size; i++){
	    	char [] charArr = strs[i].toCharArray();
	    	int charAscii = (int) charArr[0];
	    	for (int j = 0; j < 10; j++){
	    		if (bucket[charAscii - 97][j] == null){
	    			bucket[charAscii - 97][j] = strs[i];
	    			break;
	    		}
	    	}
	    }  
	   
	    //use selection sort to sort elements in the bucket
	    for (int i = 0; i < 26; i++){
	    		bucket[i] = selectionSort(bucket[i], 10);
	    }
		
		return bucket;
	}
	
	/*************************************************************
	 * selectionSort function: to sort the elements in the bucket 
	 *           
	 * @param arr, size
	 *************************************************************/
	public static String[] selectionSort ( String [] subStr, int size){
		int i, j; // index counters
		String key; // string to sort key
		 if (subStr[1] == null || subStr[0] == null){
			return subStr;
		 }
			if (subStr[1] != null){// for loop to sort the string.
			for (i = 1; i < size; i++){
				key = subStr[i];
				j = i-1;
				//While loop to move elements greater than the key.
				if (key != null &&subStr[j]!=null){
				while ( j>=0 && (subStr[j].compareToIgnoreCase(key) > 0)){
					subStr[j+1] = subStr[j];
					j = j-1;
				}
				}
				subStr[j+1] = key;
			}
			}
			return subStr;
		}
	
	
}
