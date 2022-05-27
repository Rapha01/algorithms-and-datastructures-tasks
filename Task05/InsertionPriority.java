
/***********************************************************
 * @team Djordje Nikolic, Tarek Nofal, Sebastian Hattinger
 *Aufgabe Nr 5
 **********************************************************/

import java.util.ArrayList;
import java.util.List;


/*******************************************************
 *  Insertion Priority  and RemoveFirst Queuemanagement 
 ********************************************************/
class InsertionPriority <T extends Comparable<T>> implements PriorityQueue<T>{

	/**************************************
	 * Instance Array List 
	 *************************************/
	List<T> List = new ArrayList<T>();

	/*************************************
	 * Default Constructor
	 ************************************/
	public InsertionPriority(){

	}

	/******************************************************
	 *Insertion method for placing value into right place
	 *****************************************************/
	public void insert(T e){
		T temp;                		

		List.add(e); 
		int i=List.size()-1;
		while(i >= 1 && List.get(i).compareTo(List.get(i-1)) >= 0 ){
			temp = List.get(i);  
			List.set(i, List.get(i-1));
			List.set(i-1, temp); 
			i--;
		}
	}

	/**********************************
	 * Removes first array element 
	 **********************************/
	public T removeFirst(){
		T removedElement = List.get(0);
		List.remove(0);
		return removedElement;
	}

	/* *************************************************
	 *method that gives false if no free space in array
	 * or true if array has space to fill
	 ***************************************************/
	public boolean isEmpty(){
		return List.isEmpty();
	}


	/******************************************************
	 * method that prints out the content of the array list 
	 *****************************************************/
	public void dump(){
		for (T current: List) {
			System.out.print(current + ", ");
		}	
	}


}