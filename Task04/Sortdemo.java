
/**
 * @team Djordje Nikolic, Tarek Nofal, Sebastian Hattinger
 */
import java.io.*;


public class Sortdemo {

	public static void main(String[] args) {

		String filepath = "data.txt";
		int runtimeLength =  1000;
		int intstr = 1; // 0 for int, 1 for string arrays


		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filepath));
			/**
			 * for loop stars runtime tests for i..runtimeLength elements InsertionSort(i) and Mergesort(i)
			 * @param filepath out.write writes the runtime results to the txt file specified in filepath;
			 */	
			for(int i=2; i<runtimeLength+1; i+=20){	
				out.write(i + " | "+ run(i)[0][intstr] + " | " + run(i)[1][intstr] + " | " + run(i)[2][intstr] + " | " + run(i)[3][intstr]);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {}

	}

	/** Function run creates random int and string arrays with length n (input)
	 * returns Array erg with runtime values for InsSort and MergeSort
	 */	
	public static double[][] run(int n)
	{

		java.util.Random random;
		random = new java.util.Random();

		/**
		 * @param rangemax
		 * @param basicstrings Symbols of which the random String Arrays can consist
		 * @param arrayLengths Lengths of the String, Integer and Double Arrays
		 */ 
		int rangeMax=100; // Max Value of random Double,Int (starting from 0)
		String[] basicstrings = {"a","b","c","d","e","f","g","h","i","j","k",
				"l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};       
		int stringmaxlength = 20;
		String tempString;

		/**
		 * Declaration of String- and IntegerArrays
		 * @param arrayLengths max length of the arrays
		 */
		Integer[] intArray, intArray2, intArray3, intArray4;
		intArray = new Integer[n];
		String[] strArray = new String[n];; 
		int stringlength;

		/**
		 * Declarations for the second and thrid Arrays which are a copy of the initial ones. 
		 * Necessary not to have a preordered Array in the binarysearch and mergesort.
		 *  For comparability of runtimes we need the same unordered Arrays
		 */
		intArray2 = new Integer[intArray.length];
		String[] strArray2 = new String[strArray.length]; 
		intArray3 = new Integer[intArray.length];
		String[] strArray3 = new String[strArray.length]; 
		intArray4 = new Integer[intArray.length];
		String[] strArray4 = new String[strArray.length]; 

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


		/**
		 * for comparability: copy of the Arrays to have the same Input in all sorting algorithms
		 * 
		 */
		java.lang.System.arraycopy(strArray, 0,strArray2,0,strArray.length);			
		java.lang.System.arraycopy(strArray, 0,strArray3,0,strArray.length);
		java.lang.System.arraycopy(strArray, 0,strArray4,0,strArray.length);
		
		java.lang.System.arraycopy(intArray, 0,intArray2,0,intArray.length);			
		java.lang.System.arraycopy(intArray, 0,intArray3,0,intArray.length);
		java.lang.System.arraycopy(intArray, 0,intArray4,0,intArray.length);
		
		
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
		double InsSortRunTimeMsInt;
		double InsSortRunTimeMsStr;



		beforesort = System.nanoTime();
		Sorter.insertionsort(intArray);
		aftersort = System.nanoTime();
		InsSortRunTimeMsInt = (aftersort - beforesort) / 1000000;

		beforesort = System.nanoTime();
		Sorter.insertionsort(strArray);
		aftersort = System.nanoTime();
		InsSortRunTimeMsStr = (aftersort - beforesort) / 1000000;


		/**
		 * Javasort
		 */          
		double javaSortRunTimeMsInt;
		double javaSortRunTimeMsStr;

		beforesort = System.nanoTime();
		java.util.Arrays.sort(intArray2);
		aftersort = System.nanoTime();
		javaSortRunTimeMsInt = (aftersort - beforesort) / 1000000;

		beforesort = System.nanoTime();
		java.util.Arrays.sort(strArray2);
		aftersort = System.nanoTime();
		javaSortRunTimeMsStr = (aftersort - beforesort) / 1000000;


		/**
		 * Mergesort
		 * Sort of the Arrays with Timestamp before start and after finish
		 * Runtime for 1 Algotithm = The Difference between those two Timestamps
		 * @param beforesort Timestamp before merge sort operation
		 * @param aftersort Timestamp after merge sort operation
		 * @param MergeSortRunningTimeMsInt Time for integer mergesort in milliseconds
		 * @param MergeSortRunningTimeMsStr Time for string Binary mergesort in milliseconds
		 */          
		double MergeSortRunTimeMsInt;
		double MergeSortRunTimeMsStr;

		beforesort = System.nanoTime();
		Sorter.mergesort(intArray3);
		aftersort = System.nanoTime();
		MergeSortRunTimeMsInt = (aftersort - beforesort) / 1000000;

		beforesort = System.nanoTime();
		Sorter.mergesort(strArray3);
		aftersort = System.nanoTime();
		MergeSortRunTimeMsStr = (aftersort - beforesort) / 1000000;
		
		
		/**
		 * Quicksort
		 */          
		double quickSortRunTimeMsInt;
		double quickSortRunTimeMsStr;

		beforesort = System.nanoTime();
		Sorter.quicksort(intArray4);
		aftersort = System.nanoTime();
		quickSortRunTimeMsInt = (aftersort - beforesort) / 1000000;

		beforesort = System.nanoTime();
		Sorter.quicksort(strArray4);
		aftersort = System.nanoTime();
		quickSortRunTimeMsStr = (aftersort - beforesort) / 1000000;
		
		


		/**
		 *  Check of Arrays to be sorted correctly
		 */
		boolean ordered[] = new boolean[6];
		ordered[0] = Sorter.ordercheck(intArray);
		ordered[1] = Sorter.ordercheck(strArray);
		ordered[2] = Sorter.ordercheck(intArray2);
		ordered[3] = Sorter.ordercheck(strArray2);  
		ordered[4] = Sorter.ordercheck(intArray3);
		ordered[5] = Sorter.ordercheck(strArray3); 


		/**
		 *  Dump of the Array's Runtime 
		 */             
		//System.out.println(InsSortRunningTimeMsInt + " | " + MergeSortRunningTimeMsInt);
		//System.out.println(+ InsSortRunningTimeMsStr + " | " + MergeSortRunningTimeMsStr);

		double[][] erg = new double[4][3];
		erg[0][0] = InsSortRunTimeMsInt;
		erg[0][1] = InsSortRunTimeMsStr;
		erg[1][0] = MergeSortRunTimeMsInt;
		erg[1][1] = MergeSortRunTimeMsStr;
		erg[2][0] = javaSortRunTimeMsInt;
		erg[2][1] = javaSortRunTimeMsStr;
		erg[3][0] = quickSortRunTimeMsInt;
		erg[3][1] = quickSortRunTimeMsStr;
		
		return erg;





	}
}

