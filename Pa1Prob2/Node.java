/***********************************************************
 * Node class
 * @author Benlemma
 * This class used to creat the tree.
 ***********************************************************/

public class Node {
	private String data;
	private Node left;
	private Node right;
	private int frequency;
	private String bin;
	
	/*********************************************************
	 * Contractor
	 * @param data
	 * @param left and right nodes
	 * @param frequency
	 *********************************************************/
	Node(String d, Node l, Node r, int f){
		data = d;
		frequency = f;
		left = l;
		right = r;
		bin = "";
	}
	
	/**********************************************************
	 * siLeaf method 
	 * @return checks if the node is a leaf or not 
	 ***********************************************************/
	private boolean isLeaf(){
		return(left==null && right==null);
	}
	
	/**********************************************************
	 * getBin method 
	 * @return the bin
	 ***********************************************************/
	public String getBin() {
		return bin;
	}

	/**********************************************************
	 * setName method: initializes the name of the customer
	 * @parm name
	 ***********************************************************/
	public void setBin(String b) {
		if(this.data != null){
		this.bin = b + bin;
		}
		if (!isLeaf()){
			left.setBin(b);
			right.setBin(b);
		
		}
	}

	/**********************************************************
	 * getData method 
	 * @return data
	 ***********************************************************/
	public String getData() {
		return data;
	}

	/**********************************************************
	 * setData method: initializes the data
	 * @parm data
	 ***********************************************************/
	public void setData(String data) {
		this.data = data;
	}

	/**********************************************************
	 * getLeft method 
	 * @return returns the left node
	 ***********************************************************/
	public Node getLeft() {
		return left;
	}
	/**********************************************************
	 * setLeft method: initializes the left Node
	 * @parm left
	 ***********************************************************/
	public void setLeft(Node left) {
		this.left = left;
	}

	/**********************************************************
	 * getRight method 
	 * @return the right node
	 ***********************************************************/
	public Node getRight() {
		return right;
	}

	/**********************************************************
	 * setRight method: initializes the right node
	 * @parm right
	 ***********************************************************/
	public void setRight(Node right) {
		this.right = right;
	}
	
	/**********************************************************
	 * getFrequency method 
	 * @return the frequency of the data
	 ***********************************************************/
	public int getFrequency() {
		return frequency;
	}

	/**********************************************************
	 * setFrequency method: initializes the frequency
	 * @parm frequency
	 ***********************************************************/
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**********************************************************
	 * compaeTo method 
	 * @return compare two nodes ( the frequency difrence )
	 ***********************************************************/
	public int compareTo(Node newNode) {
		// TODO Auto-generated method stub
		return this.frequency - newNode.frequency;
	}

	//toString metod to return the bin value of leaf nodes.
	public String toString() {
		if(isLeaf()){
			return this.data + ":" + this.bin + "\n";
		}  else {
		return this.left.toString() + this.right.toString();
		}
	}

	
}
