
/***********************************************************
 * @team Djordje Nikolic, Tarek Nofal, Sebastian Hattinger
 *Aufgabe Nr 5
 **********************************************************/

import java.io.*;
import java.util.*;

public class QueueDemo {

	public static void main(String[] args) {

		/*******************
		 *initial Values
		 ******************/


		int initialGenerate = 1000;
		int inputFaktor = 100000;
		int val = 40;
		int intRange = 100;

		/****************************************************
		 *Buffer to save Entered Value from The Keyboard and 
		 *catch exception if entered value is not integer value
		 ****************************************************/
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try{
			System.out.println("Enter Int: ");
			val = Integer.parseInt(br.readLine());

		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
		}	
		 

		/********************
		 * Initialize Classes
		 ********************/
		InsertionPriority pqIns = new InsertionPriority();
		HeapPriority pqHeap = new HeapPriority();


		/**************************************
		 *Filling of the Lists with random values
		 ***************************************/
		for(int i=0;i<initialGenerate;i++){
			pqIns.insert((int)(Math.random()*intRange));	
		}

		for(int i=0;i<initialGenerate;i++){
			pqHeap.insert((int)(Math.random()*intRange));	
		}


		/**********************************
		 *Queues measuring runtime
		 **********************************/
		double beforesort;
		double aftersort;
		double InsRunTimeMs;
		double HeapRunTimeMs;

		System.out.println("Erased Elements Insertion: ");
		beforesort = System.nanoTime();
		for(int i=0;i<(val*inputFaktor);i++){

			if(Math.random()<0.5 || pqIns.isEmpty()) 
				pqIns.insert((int)(Math.random()*intRange)); 

			else {
				System.out.print(pqIns.removeFirst() + ", ");
				//pqIns.removeFirst();

			}
		}

		System.out.println();
		System.out.println("Erased Elements Heap: ");
		aftersort = System.nanoTime();
		InsRunTimeMs = (aftersort - beforesort) / 1000000;

		beforesort = System.nanoTime();
		for(int i=0;i<(val*inputFaktor);i++){

			if(Math.random()<0.5 || pqHeap.isEmpty())
				pqHeap.insert((int)(Math.random()*intRange));
			else {
				System.out.print(pqHeap.removeFirst() + ", ");
				//pqHeap.removeFirst();
			}			
		}
		aftersort = System.nanoTime();
		HeapRunTimeMs = (aftersort - beforesort) / 1000000;


		/*********************************************
		 *printing out the results with processing time
		 *********************************************/

		//pqHeap.dump();
		System.out.println();
		System.out.println();
		System.out.println("Ins Runtime: " + InsRunTimeMs + " ms");
		System.out.println("Heap Runtime: " + HeapRunTimeMs+ " ms"); 

	}
}

