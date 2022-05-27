/***********************************************************
 * @team Djordje Nikolic, Tarek Nofal, Sebastian Hattinger
 * Aufgabe Nr 7
 **********************************************************/
import java.awt.*;
public class AVLBalanced  <T extends Comparable<T>> implements BinaryTreeInterface<T> {

	// Instance root element
	public Node root;
	public int PieceCounter = 0;
	public int counter=0;
	
	
	/**********************************
	 * Constructor 1 for an empty tree 
	 **********************************/
	public AVLBalanced () { 
		this.root = null;
	}

	/***********************************
	 *Constructor 2 for array insertion
	 ***********************************/
	public AVLBalanced (T[] data) {
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
		    root =  remove(root,Piece);
		  
		}
		
		else{
		
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
			rootNode.height = testHeight(rootNode);                    

            if (testHeight(rootNode.right) - testHeight(rootNode.left) == 2) {
                if (testHeight(rootNode.right.left) <= testHeight(rootNode.right.right)){
                     rootNode = leftRotate(rootNode);                           
                }
                else{
                    
                    rootNode.right = rightRotate(rootNode.right);              
                    rootNode = leftRotate(rootNode);
                    }
                }

        return rootNode;
			
			
		}
           // if node to be deleted > root
          // then go right
		
		else {
			rootNode.right = remove(rootNode.right, Piece);
			rootNode.height = testHeight(rootNode);                    

            if (testHeight(rootNode.left) - testHeight(rootNode.right) == 2){   
                if (testHeight(rootNode.left.left) >= testHeight(rootNode.left.right)){
                    
                        rootNode = rightRotate(rootNode);                          
                }
                
				else{
                    
                        rootNode.left = leftRotate(rootNode.left);                 
                        rootNode = rightRotate(rootNode);
                }
            }
        return rootNode;
			 
			
			
			
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

	
	 private Node insert(Node node, T item)           
    {                                                
        if (node == null)                            
        {
            return new Node(item,null, null);        
        }

        else if (item.compareTo(node.data) <= 0)      
        {
            node.left = insert(node.left, item);     
            node.height = testHeight(node);                        

            if (testHeight(node.left) - testHeight(node.right) == 2)  
            {                                                         
                if (item.compareTo(node.left.data) <= 0)               
                {
                    node = rightRotate(node);                         
                }
                else                                                  
                {
                    node.left = leftRotate(node.left);                
                    node = rightRotate(node);
                }
            }
            return node;
        }

        else if (item.compareTo(node.data) > 0)                    
        {
            node.right = insert(node.right, item);                 
            node.height = testHeight(node);                        

            if (testHeight(node.right) - testHeight(node.left) == 2)  
            {                                                         
                if (item.compareTo(node.right.data) > 0)              
                {
                    node = leftRotate(node);                          
                }
            
                else                                                  
                {
                    node.right = rightRotate(node.right);             
                    node = leftRotate(node);
                }
            }
            return node;
        }
      
        else                                                       
            return node;
    }

	
	   private int testHeight(Node node)   {                            
  
        if (node == null)                                              
            return -1;                                                 
        else if (node.left == null && node.right == null)              
            return 0;                                                  
        else if (node.left == null)                                    
            return node.right.height + 1;                              
        else if (node.right == null)                                   
            return node.left.height + 1;                               
        else                                                           
            return Math.max(node.left.height, node.right.height) + 1;  
    }
	
	
	private Node rightRotate(Node node)                      
    {
        Node temp;
        temp = node.left;
        node.left = temp.right;
        temp.right = node;
        node.height = testHeight(node);
        temp.height = testHeight(temp);
        return temp;
    }

	 private Node leftRotate(Node node)                       
    {
        Node temp;
        temp = node.right;
        node.right = temp.left;
        temp.left = node;
        node.height = testHeight(node);
        temp.height = testHeight(temp);
        return temp;
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
		int messedHeight = root.height - drawNode.height;
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