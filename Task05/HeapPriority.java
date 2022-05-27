
/***********************************************************
 * @team Djordje Nikolic, Tarek Nofal, Sebastian Hattinger
 *Aufgabe Nr 5
 **********************************************************/

import java.util.ArrayList;
import java.util.List;


/*****************************************************
 *  Heap Insert and RemoveFirst Queuemanagement 
 *****************************************************/

class HeapPriority <T extends Comparable<T>> implements PriorityQueue<T>{

	/***********************
	 * Instance Array List
	 ***********************/
	List<T> List = new ArrayList<T>();


	/*******************************
	 * default Constructor *
	 ***************************** */
	public HeapPriority(){ 

	}



	/*****************************************************
	 *Inserts process priority into The array and 
	 *Call Upheap To Keep the Heap Order Property
	 *****************************************************/

	public void insert(T e){
		List.add(e);
		upheap(List.size()-1);
	}

	/*********************************
	 *Keep the Heap Order property
	 **********************************/


	public void upheap(int chiPos){

		int parPos;
		T temp;
		if(chiPos>=1)  
		{ 
			parPos=chiPos/2;  
			if(List.get(parPos).compareTo(List.get(chiPos)) < 0)  
			{  
				temp = List.get(chiPos);  
				List.set(chiPos, List.get(parPos));
				List.set(parPos, temp); 
				upheap(parPos);  
			}  	       
		}  
	}


	/*************************************************
	 * Remove first place of array  
	 *************************************************/
	public T removeFirst(){
		T removedElement = List.get(0);
		List.remove(0);
		return removedElement;
	}


	/*************************************************
	 * method that gives false if no free space in array
	 *  or true if array has space to fill 
	 *********************************************** */
	public boolean isEmpty(){
		return List.isEmpty();
	}



	/***************************************************
	 * method that prints out the content of the array list 
	 ***************************************************/
	public void dump(){
		for (T current: List) {
			System.out.print(current + ", ");
		}	
	}

}