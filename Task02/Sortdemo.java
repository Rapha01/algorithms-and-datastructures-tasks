
/**
 * @team Djordje Nikolic, Tarek Nofal, Sebastian Hattinger
 */



public class Sortdemo {

	public static void main(String[] args) {

		java.util.Random random;
		random = new java.util.Random();
		// Random rand = new Random();

		/**
		 * @param rangemax
		 * @param basicstrings Symbols of which the random String Arrays can consist
		 * @param arrayLengths Lengths of the String, Integer and Double Arrays
		 */ 
		int maxArrayLengths = 200;
		int rangeMax=100; // Max Value of random Double,Int (starting from 0)
		String[] basicstrings = {"a","b","c","d","e","f","g","h","i","j","k",
				"l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};       
		int stringmaxlength = 20;
		String tempString;

		/**
		 * Declaration of String-,Integer- and Double-Arrays
		 * Usage of class Random for randomized Arrayslenghts
		 * @param arrayLengths max length of the arrays
		 */
		Integer[] intArray;
		intArray = new Integer[random.nextInt(maxArrayLengths)];
		String[] strArray = new String[random.nextInt(maxArrayLengths)];; 
		Double[] doubleArray;
		doubleArray = new Double[random.nextInt(maxArrayLengths)];
		int stringlength;

		/**
		 * Declarations for the second Arrays which are a copy of the initial ones. 
		 * Necessary not to have a preordered Array in the binarysearch.
		 *  For comparability of runtimes we need the same unordered Arrays
		 */
		Integer[] intArray2;
		intArray2 = new Integer[intArray.length];
		String[] strArray2 = new String[strArray.length]; 
		Double[] doubleArray2;
		doubleArray2 = new Double[doubleArray.length];

		/**
		 * Filling of the created Arrays with random values
		 * @param intArray Array with random integer values of length arrayLenghs
		 * @param strArray Array with random string values of length arrayLenghs
		 * @param doubleArray Array with random double values of length arrayLenghs
		 */

		//randInt
		for(int i=0;i<intArray.length;i++){
			intArray[i] = random.nextInt(rangeMax);
		}

		//randString
		for(int i=0;i<strArray.length;i++){
			stringlength= random.nextInt(stringmaxlength);
			tempString = "";
			for(int j=0;j<stringlength;j++){
				tempString += basicstrings[random.nextInt(basicstrings.length)];
			}
			strArray[i] = tempString;
		}   

		//randDouble
		for(int i=0;i<doubleArray.length;i++){
			double result = (double)(random.nextDouble()* rangeMax);
			doubleArray[i] = result;
		}

		/**
		 * Copy of the Arrays to have two of the same, one for insertion,
		 * the other for binary insertion sort
		 */
		for(int i=0; i<intArray.length; i++){
			intArray2[i]=intArray[i];
		}
		for(int i=0; i<strArray.length; i++){
			strArray2[i]=strArray[i];
		}
		for(int i=0; i<doubleArray.length; i++){
			doubleArray2[i]=doubleArray[i];
		}
		
		/**
		 * INSERTIONSORT
		 * Sort of the Arrays with Timestamp before start and after finish
		 * Runtime for 1 Algotithm = The Difference between those two Timestamps
		 * @param beforesort Timestamp before a sort operation
		 * @param aftersort Timestamp after sort operation
		 * @param InsSortRunningTimeMsInt Time for integer Insertionsort in milliseconds
		 * @param InsSortRunningTimeMsStr Time for string Insertionsort in milliseconds
		 * @param InsSortRunningTimeMsDouble Time for double Insertionsort in milliseconds
		 */
		double beforesort;
		double aftersort;
		double InsSortRunningTimeMsInt;
		double InsSortRunningTimeMsStr;
		double InsSortRunningTimeMsDouble;



		beforesort = System.nanoTime();
		Sorter.insertionsort(intArray);
		aftersort = System.nanoTime();
		InsSortRunningTimeMsInt = (aftersort - beforesort) / 1000000;

		beforesort = System.nanoTime();
		Sorter.insertionsort(strArray);
		aftersort = System.nanoTime();
		InsSortRunningTimeMsStr = (aftersort - beforesort) / 1000000;

		beforesort = System.nanoTime();
		Sorter.insertionsort(doubleArray);
		aftersort = System.nanoTime();
		InsSortRunningTimeMsDouble = (aftersort - beforesort) / 1000000;

		/**
		 *  Check of Arrays to be sorted correctly
		 */
		boolean ordered[] = new boolean[6];
		ordered[0] = Sorter.ordercheck(intArray);
		ordered[1] = Sorter.ordercheck(strArray);
		ordered[2] = Sorter.ordercheck(doubleArray);


		/**
		 * Binary INSERTIONSORT
		 * Sort of the Arrays with Timestamp before start and after finish
		 * Runtime for 1 Algotithm = The Difference between those two Timestamps
		 * @param beforesort Timestamp before binary sort operation
		 * @param aftersort Timestamp after binary sort operation
		 * @param BinaryInsSortRunningTimeMsInt Time for integer  Binary Insertionsort in milliseconds
		 * @param BinaryInsSortRunningTimeMsStr Time for string Binary Insertionsort in milliseconds
		 * @param BinaryInsSortRunningTimeMsDouble Time for double Binary Insertionsort in milliseconds
		 */          
		double BinaryInsSortRunningTimeMsInt;
		double BinaryInsSortRunningTimeMsStr;
		double BinaryInsSortRunningTimeMsDouble;

		beforesort = System.nanoTime();
		Sorter.insertionsortEnhanced(intArray2);
		aftersort = System.nanoTime();
		BinaryInsSortRunningTimeMsInt = (aftersort - beforesort) / 1000000;

		beforesort = System.nanoTime();
		Sorter.insertionsortEnhanced(strArray2);
		aftersort = System.nanoTime();
		BinaryInsSortRunningTimeMsStr = (aftersort - beforesort) / 1000000;

		beforesort = System.nanoTime();
		Sorter.insertionsortEnhanced(doubleArray2);
		aftersort = System.nanoTime();
		BinaryInsSortRunningTimeMsDouble = (aftersort - beforesort) / 1000000;

		/**
		 *  Check of Arrays to be sorted correctly
		 */
		ordered[3] = Sorter.ordercheck(intArray2);
		ordered[4] = Sorter.ordercheck(strArray2);
		ordered[5] = Sorter.ordercheck(doubleArray2);    




		/**
		 *  Dump of the Arrays
		 */      
		/*
		//Integer Array
		for(int i=0;i<intArray.length;i++){
			System.out.println(intArray[i]+", ");            
		}


		//String Array
		for(int i=0;i<strArray.length;i++){
			System.out.println(strArray[i]+", ");            
		}
		;

		//Double Array
		for(int i=0;i<doubleArray.length;i++){
			System.out.println(doubleArray[i]+", ");
		}
        */
		
		
		/**
		 *  Dump of the Array sort test
		 */    
		System.out.println("Integer array sorted: "+ ordered[0]);
		System.out.println("String array sorted: "+ ordered[1]);
		System.out.println("Double array sorted: "+ ordered[2]);
		System.out.println("Integer array binary sorted: "+ ordered[3]);
		System.out.println("String array binary sorted: "+ ordered[4]);
		System.out.println("Double array binary sorted: "+ ordered[5]);
		System.out.println();System.out.println();

		/**
		 *  Dump of the Array's Runtime 
		 */             
		System.out.println("Array Type:  n  | Ins Sort Runtime | BinaryIns Sort Runtime");
		//int Array length and runtime dump 
		System.out.println("Integer Array: " + intArray.length + " | " + InsSortRunningTimeMsInt + "ms | " + BinaryInsSortRunningTimeMsInt+"ms");
		//str Array length and runtime dump
		System.out.println("String Array: " + strArray.length + " | " + InsSortRunningTimeMsStr + "ms | " + BinaryInsSortRunningTimeMsStr+"ms");
		//double Array length and runtime dump
		System.out.println("Double Array: " + doubleArray.length + " | "+ InsSortRunningTimeMsDouble + "ms | " + BinaryInsSortRunningTimeMsDouble+"ms");
	}
}
