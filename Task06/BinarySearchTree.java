/***********************************************************
 * @team Djordje Nikolic, Tarek Nofal, Sebastian Hattinger
 * Aufgabe Nr 6
 **********************************************************/
import java.awt.*;
public class BinarySearchTree <T extends Comparable<T>> implements BinaryTreeInterface<T> {

	// Instance root element
	public Node root;
	public int PieceCounter = 0;
	public int counter=0;
	
	
	/**********************************
	 * Constructor 1 for an empty tree 
	 **********************************/
	public BinarySearchTree() { 
		this.root = null;
	}

	/***********************************
	 *Constructor 2 for array insertion
	 ***********************************/
	public BinarySearchTree(T[] data) {
		// if array is empty make empty tree  
		if(data.length == 0)
			this.root = null;
		else {
			// take array element 0 as root
		
			this.root = new Node(data[0]);   
	
			// insert following data    
			for (int i = 1; i < data.length; i++){
				this.insert(data[i]);
			}
		}
	}


	
	/****************************
	 *	runs method insert **
	 *****************************/
	public void insert(T piece) {
		counter++;
		root = this.insert(root, piece);	
	}

	/***************************
	 *  runs method getHeight
	 ***************************/
	public int getHeight() {
		return drawHeight();
	}

	/*****************************
	 *runs Search method 
	 *****************************/
	public T search(T piece) {
		return search(piece, root);
	}

	/*****************************
	 *runs Remove  method 
	 *****************************/
	public void remove(T Piece){
		if (search(Piece) != null){
		     PieceCounter=0;
		     count(Piece, root);
            
		     
		   //System.out.println(PieceCounter);
		 // for(int i=0; i<PieceCounter;i++){
		     root =  remove(root,Piece);
		        
		  //}
		}
		else
		{
			System.out.println("Element Does not Exist !");
		}
	     
	
	}

	/************************************
	 * Inner Class for saving Node Data
	 ***********************************/
	private class Node { 

		/*****************************
		 * Variables for Inner Class 
		 ****************************/
		T data;        // head data 
		Node left;  // left Node 
		Node right; // right node 
		int height;    // the height of the partial tree  


		/************************************************
		 * Node Constructor 1 for calling constructor 2
		 * with only head filled
		 ***********************************************/
		private Node(T data) {
			this(data, null, null);
		}


		/**********************************************
		 * Node Constructor 2 for saving parameters
		 * head = data, left = left, right = right
		 **********************************************/
		/******************************************************
		 * every Node is an Object of this private class 
		 * NODE and is initialised via this second constructor
		 *****************************************************/
		private Node(T data, Node left, Node right) { 
			this.data = data;
			this.left = left;
			this.right = right;
			height = 0;
		     
		 }    

	}



	/***********************************
	 * Search, Remove , Insert Methods
	 ***********************************    


   /****************************************************
	 * Search Method 
	 * runs search method with searched piece and root  
	 * find a node beginning to search from the rootNode
	 ****************************************************/
	public  T search(T piece, Node rootNode) {     

		if (rootNode == null)                          
			return null;
		// search in left child if piece < root	
		else if (piece.compareTo(rootNode.data) < 0)    
			return search(piece, rootNode.left); 
		//return piece if piece == root	
		else if (piece.compareTo(rootNode.data) == 0)   
			return piece;
		// search in right child if piece > root		
		else
			return search(piece, rootNode.right);        
	}
	
	/****************************************
	 * Remove Method
	 *****************************************/ 


	public Node remove( Node rootNode, T Piece ){
		// if tree is empty
		if ( rootNode == null ) {			
			return null;
		}
		
		 // check node to be removed 
		if ( Piece == rootNode.data ){
		// check if left child is empty
		// and return the right subtree
			if(rootNode.left == null){
			
				return rootNode.right;
			} 
			 //check if right child is empty
			 //and return the left subtree
			else if(rootNode.right == null){
				return rootNode.left; // return remove(rootNode.left,Piece);
			}

			else { //both the children exists

				Node successor = getSuccessorNode(rootNode.left);
				rootNode.data = successor.data;
				rootNode.left = remove(rootNode.left, successor.data);
			}
			
		}
		 // if node to be deleted <= root
        // then go left   
		else if (Piece.compareTo( rootNode.data) <= 0){				
			rootNode.left = remove(rootNode.left, Piece);
		}
           // if node to be deleted > root
          // then go right
		
		else {
			rootNode.right = remove(rootNode.right, Piece);
		}

		return rootNode;
	}
	
	 /********************************************
     * get successor method 
     * to get the predecessor of the current node
     ********************************************/

	public Node getSuccessorNode( Node rootNode){
          // get maximum of the left subtree //
         // or minimum of right sub tree 
		while(rootNode.right != null){
			rootNode = rootNode.right;
		}
		return rootNode;
	}

	
	
	/***************************************************
	 * Insert Method runs insert method with recursive
	 * changing rootNode and "toIns(erted)" piece
	 * returns inserted Node
	 ***************************************************/
	public Node insert(Node rootNode, T toIns) { 

	    // if root is empty create root 
		//with given value
		if (rootNode == null){                   
			rootNode = new Node(toIns, null, null);
			return rootNode;
		}
			
		int comparison = rootNode.data.compareTo(toIns);

		 // insert left if node < root
         // and left is empty 
		
		if(comparison >= 0){
			if(rootNode.left == null){
				rootNode.left= new Node (toIns);
			}
             // if left is not empty
             // call insert on left again till find empty place
 
			else {
				insert (rootNode.left , toIns);
			}
		}
		 //if node > root and right is empty 
        // then insert right 
		
		else {
			if(rootNode.right == null){
				rootNode.right = new Node (toIns);
			}
             //if node > root and right is not empty 
            // then call insert on right till find empty place
			
			else{
				insert (rootNode.right , toIns);
			}


		}

		return rootNode;

	} 

	private void count(T Piece, Node rootNode) {
		// counts only if root is not empty
		if(rootNode != null){
		 // if duplicate is found 
		 //increase counter
		if (rootNode.data == Piece)                          
			PieceCounter++;
			
		//check duplicates starting from left child
		if(rootNode.left != null)
		count(Piece, rootNode.left);
		//check duplicates starting from right child
		if(rootNode.right != null)
			count(Piece, rootNode.right);
		}
	}
	
	
	   
	
	
	

	/****************************
	 *   DrawHeight Method 
	 ***************************/
	public int drawHeight(){ 
		int niveau = 0;
		for (int i=1;i<=counter;i=i*2){
			niveau++;
		}
		return niveau-1;
	}
	
	/********************************************
    Draw Method Of The Tree
	 * Method for drawing Ovals + their lines 
	 * Input is a Node, the graphics element,
	 * x , y, x+ , y+ and sideDepth
	 * x+ and y+ stand for next coordinate and 
	 * sideDepth for sideDepth of the tree  
	 *********************************************/
	public void drawme(Node drawNode, Graphics g, int x, int y, int xNext, int yNext, int sideDepth)
	{
		//if (drawNode != null){
		// y coordinate intervall
		int downDepth = 65;
		// radia of element circles
		int radia = 11;

		// Draw Element Circle 
		int messedHeight = root.height - drawNode.height-1;
		g.drawOval(x , y , radia * 2 , radia * 2);

		// Write number into circle (dependence of string length) 
		int stringPosition = String.valueOf(drawNode.data).length() * 3;
		g.drawString( String.valueOf(drawNode.data) , x + radia - stringPosition - 2 , y + radia + 5 );

		// Draw Line to next Point (if existing (height != 0)) 
		if (messedHeight != 0){
			g.drawLine(x + radia, y, xNext + radia, yNext);
		}   

		// recursive call for drawing left and right child
		// left child
		if (drawNode.left != null){
			drawme(drawNode.left , g, /*X: */ x - ( 800 / (int)Math.pow(2, sideDepth+2) ), /*Y: */ y + downDepth, /*X+: */ x , /*Y+: */ y + radia * 2, sideDepth+1);
		}

		// right child
		if (drawNode.right != null){
			drawme(drawNode.right, g, /*X: */ x + ( 800 / (int)Math.pow(2, sideDepth+2) ), /*Y: */ y + downDepth, /*X+: */ x , /*Y+: */ y + radia * 2, sideDepth+1);
		}
		//}
	}

}