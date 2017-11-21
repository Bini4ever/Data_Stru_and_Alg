import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/***********************************************************
 * Huffman class
 * @author Benlemma
 * This class used to build a data compression system 
 * using the Huffman's algorithm. 
 ***********************************************************/

public class Huffman {
	public static char[] chars = new char[26];
	public static int countchar = 0;
	public static Node[] nodes = new Node[chars.length];
	
	/***********************************************************
	 * Main function: a driver function that reads the file 
	 *                and collect the charactors and count 
	 *                and stor frequncy of each charactors.
	 * @param args
	 ***********************************************************/
	public static void main(String[] args){
		
		
		String str = "";
		boolean b = true;
		
		
		// read the file
		try {
			Scanner input=  new Scanner(new File(args[0]));
			while(input.hasNext())
			{
				str += input.nextLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No file \""+args[0]+"\" found!");
		}
		
		
		// change the string to char arrry 
		char[] allChar = str.toCharArray();
		
		// outer loop over allchar
		// add new char to the chars array 
		for (int i = 0; i < allChar.length; i++)
		{
			for (int j = 0; j < chars.length; j++)
			{
				if (chars[j] == allChar[i]){
					b = false;
				}
			}
			if (b)
			{
				chars[countchar] = allChar[i];
				countchar++;
			}
			else
				b = true;
		}
		
		// creat an array of char to store the chars from the file
		int[] numChar = new int[chars.length];
		for (int i = 0; i < chars.length; i++)
		{
			numChar[i] = 0;
		}
		
		//count the frequency
		for( int i = 0; i < numChar.length; i++)
		{
			for (int j = 0; j < allChar.length; j++)
			{
				if(chars[i] == allChar[j])
				{
					numChar[i] += 1;
				}
			}
		}
		
		// sort using heapsort
		numChar = heapSort(numChar);
		// creat huffmanTree
		huffmanTree(numChar);
		
		// rewrite to the file.
		try {
			PrintWriter output = new PrintWriter(new File("output.txt"));
			output.print(nodes[0].toString());
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error creating \"output.txt\"!");
		}
		System.out.println("output.txt created!");
	
		
	}
	
	/***********************************************************
	 * heapSort function: sorts array. 
	 * @param 
	 * @return 
	 ***********************************************************/
	public static  int[] heapSort (int[] n){
		int s = chars.length;
		for (int i = ((s/2)); i>=0; i--){
			heapify(n, i, s-1);
		}
		for (int i = s-1; i>0; i--){
			char c1 = chars[0];
			int n1 = n[0];
			chars[0] = chars[i];
			n[0] = n[i];
			chars[i] = c1;
			n[i] = n1;
			heapify(n, 0, i-1);
			
		}
		return n;
	}

	/***********************************************************
	 * heapSort function: helper function to sort the array
	 * @param c(frequency list)
	 * @param x(parent node)
	 * @param z(child node)
	 ***********************************************************/
	public static void heapify(int[] n, int x, int z){
		int y;
		char t;
		int t2;
		
		while (2*x <= z){
			if (x*2 == z){
				y = x*2;
			}
			else if ( n[x*2] < n[x*2 + 1]){
				y = x*2;
			}
			else 
				y = x*2+1;
			
			if ( n[x] > n[y]){
				t = chars[x];
				t2 = n[x];
				chars[x] = chars[y];
				n[x] = n[y];
				chars[y] = t;
				n[y] = t2;
				x = y;
			}
			else 
				break;

		}
		
	}
	
	/***********************************************************
	 * HuffmanTree function: creat nodes and build huffman tree.
	 * @param f(frequncy list)
	 * 
	 ***********************************************************/
	public static void huffmanTree(int[] f){
		
		//for loop to creat nodes for each charactors and build huffmanTree
		for (int i = 0; i < chars.length; i++){
			nodes[i] = new Node("" + chars[i], null, null, f[i]);
		}
		
		sortNode(nodes);
		makeTree();
			
	}
	
	/***********************************************************
	 * makeTree function: set approprate bin to the nodes
	 ***********************************************************/
	public static void makeTree(){
		
		while (countchar > 1){
			for (int i = 0; i < countchar-1; i++){
				if (i == countchar-2){
					nodes[i+1].setBin("0");
					nodes[i].setBin("1");
					Node newNode = new Node(nodes[i].getData()+nodes[i+1].getData(), 
							                nodes[i+1], nodes[i], 
							                nodes[i].getFrequency()+nodes[i+1].getFrequency());
					nodes[i] = newNode;
					nodes = Arrays.copyOf(nodes,countchar-1);
					sortNode(nodes);
					countchar--;
				}
			}
		}

	}
	/***********************************************************
	 * sortNode function: sorts the noes in the tree using 
	 *                    heapsort
	 * @param node
	 ***********************************************************/
	public static  void sortNode(Node[] n){
		int s = n.length;
		for (int i = s/2; i>=0; i--){
			heapifyNode(n, i, s-1);
		}
		for (int i = s-1; i>0; i--){
			Node t = n[0];
			n[0] = n[i];
			n[i] = t;
			heapifyNode(n, 0, i-1);
		}
	}
	/***********************************************************
	 * heapifyNode function: helper function of heap sort.
	 * @param node
	 * @param x, z 
	 ***********************************************************/
	public static void heapifyNode(Node[] n, int x, int z){
		int y; 
		Node t;
		
		while (2*x <= z){
			if (x*2 == z){
				y = x*2;
			}
			else if(n[x*2].getFrequency() < n[x*2 + 1].getFrequency()){
				y=x*2;
			}
			else 
				y = x*2+1;
			if (n[x].getFrequency() > n[y].getFrequency()){
				t = n[x];
				n[x] = n[y];
				n[y] = t;
				x = y;
			}
			else 
				break;
		}
	}
}
