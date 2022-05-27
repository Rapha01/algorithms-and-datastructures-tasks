/***********************************************************
 * @team Djordje Nikolic, Tarek Nofal, Sebastian Hattinger
 * Aufgabe Nr 6
 **********************************************************/
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;

public class TreeDemo {
	public static BinarySearchTree<Integer> tree1 = null;    // Normal insert Test Object


	public static void main(String[] args) {

		System.out.println("Type Int List separated by comma: (ex.: 11,5,8)");

		/*
		 * System readIn
		 */

		String readIn = "";
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);	    

		try
		{
			readIn = reader.readLine(); 
		}
		catch(Exception e){}

		String[] parts;
		parts = readIn.split(",");
		Integer[] testvalues =new Integer[parts.length];
		for (int i = 0; i < parts.length; ++i) {
			testvalues[i] = Integer.parseInt(parts[i].trim());
		}   
		//Integer[] testvalues2 = {13, 20, 8, 2, 13, 14, 20, 8, 20};

		
		/*
		 * Creation of the Tree and its draw
		 */	
		tree1 = new BinarySearchTree<Integer>(testvalues);
		TreeGUI gui = new TreeGUI();
			

		/*
		 * Loop, while tree is not empty: 
		 * Ask for the element to be removed, remove it, and draw a new tree
		 */
		Integer IntInput = 0;
		
		while(tree1.root != null){

			System.out.println("Which Element Should Be Removed ?");
			try
			{
				IntInput = Integer.parseInt(reader.readLine()); 
			}
			catch(Exception e){}
			
			tree1.remove(IntInput);

			if(tree1.root != null){
				gui.update();
			}
			else{
				System.out.println("Tree is empty");
				System.exit(0);
			}
		}

	}

	
	/* 
	 * Draw method gives root as input, g as graphical interface,
	 * 400 as first x coordinate, 20 as first y coordinate,
	 * x+ = 0; y+ = 0
	 */
	public static void draw(Graphics g){
		tree1.drawme(tree1.root,g, 400, 20, 0, 0,0);
	}

}
