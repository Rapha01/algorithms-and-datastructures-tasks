
/**
 * @team Djordje Nikolic, Tarek Nofal, Sebastian Hattinger
 */

/**
 * Class Sorter contains insertion sort with runtime O(n2) and
 * Binary insertion sort (insertionsort Enhanced) with runtime O(n*log(n))
 */
public class Sorter {


	/**
	 * Insertionsort
	 * @param a an array of Comparable items.
	 * @param compareto compares 2 values concerning order and return -1, 0 or 1 for greater, equals or lower
	 * @param temp current value to be compared to the next lower values (and swapped, if necessary)
	 */
	public static <T extends Comparable<T>> void insertionsort(T[] a) {
		int j;
		T temp;

		for (int i = 1; i < a.length; i++) {
			temp = a[i];
			j = i - 1;

			while (j >= 0 && a[j].compareTo(temp) > 0) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = temp;
		}

	} // end insertionsort


	/**
	 * Binary Insertion sort
	 * @param BinarySearch
	 */
	public static <T extends Comparable<T>> void insertionsortEnhanced(T[] a)
	{
		int ins, i, j;
		T tmp;

		for (i = 1; i < a.length; i++) {
			ins = BinarySearch (a, 0, i, a[i]);
			tmp = a[i];
			for (j = i - 1; j >= ins; j--)
				a[j + 1] = a[j];
			a[ins] = tmp;
		}
	}


	/**
	 * Binary search method
	 * searches in a[1...i] for the right index to place a[i] (key)
	 */

	public static <T extends Comparable<T>> int BinarySearch(T a[], int low, int high, T key)
	{
		int mid;

		if (low == high)
			return low;

		mid = low + ((high - low) / 2);

		if (a[mid].compareTo(key) < 0 )
			return BinarySearch (a, mid + 1, high, key);
		else if (a[mid].compareTo(key) > 0 )
			return BinarySearch (a, low, mid, key);

		return mid;
	}

	/**
	 * @param ordercheck method that checks an array of generic type T for an ascending order.
	 * returns true if ordered correctly
	 */
	public static <T extends Comparable<T>> boolean ordercheck(T[] a){

		for (int i = 0; i < a.length-1; i++) {
			if(a[i].compareTo(a[i+1]) > 0){
				return false;                
			}
		}
		return true;
	}


	//MERGESORT
	public static <T extends Comparable<T>> void mergesort(T[] a) {
		Comparable [ ] tmpArray = new Comparable[ a.length ];
		mergesort( a, tmpArray, 0, a.length - 1 );
	}

	/**
	 * Internal method that makes recursive calls.
	 * @param tmpArray an array to place the merged result.
	 * @param left the left-most index of the subarray.
	 * @param right the right-most index of the subarray.
	 */
	private static void mergesort( Comparable [ ] a, Comparable [ ] tmpArray,
			int left, int right ) {
		if( left < right ) {
			int center = ( left + right ) / 2;
			mergesort( a, tmpArray, left, center );
			mergesort( a, tmpArray, center + 1, right );
			merge( a, tmpArray, left, center + 1, right );
		}
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * @param tmpArray an array to place the merged result.
	 * @param leftPos the left-most index of the subarray.
	 * @param rightPos the index of the start of the second half.
	 * @param rightEnd the right-most index of the subarray.
	 */
	private static void merge( Comparable [ ] a, Comparable [ ] tmpArray,
			int leftPos, int rightPos, int rightEnd ) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while( leftPos <= leftEnd && rightPos <= rightEnd )
			if( a[ leftPos ].compareTo( a[ rightPos ] ) <= 0 )
				tmpArray[ tmpPos++ ] = a[ leftPos++ ];
			else
				tmpArray[ tmpPos++ ] = a[ rightPos++ ];

		while( leftPos <= leftEnd )    // Copy rest of first half
			tmpArray[ tmpPos++ ] = a[ leftPos++ ];

		while( rightPos <= rightEnd )  // Copy rest of right half
			tmpArray[ tmpPos++ ] = a[ rightPos++ ];

		// Copy tmpArray back
		for( int i = 0; i < numElements; i++, rightEnd-- )
			a[ rightEnd ] = tmpArray[ rightEnd ];
	}



} //end class Sorter

/**
 *Source Binary Insertion sort:
 * Analysis And Design Of Algorithms
 * A.A.Puntambekar - 2008
 * http://books.google.at/books?id=NUW_5rs6K-wC&pg=SA6-PA58&lpg=SA6-PA58&dq=%22BinarySearch+%28a,+0,+i,+a[i]%29%22&source=bl&ots=vznUvlhn5O&sig=abUFFD60IKUT9wbSMCqQcFUKCQk&hl=de&sa=X&ei=caokU6ObAYeM0AWM4oCgBQ&ved=0CDEQ6AEwAA#v=onepage&q=%22BinarySearch%20%28a%2C%200%2C%20i%2C%20a[i]%29%22&f=false
 */


