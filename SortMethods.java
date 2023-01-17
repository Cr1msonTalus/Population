import java.util.List;
import java.util.ArrayList;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Talus Leong
 *	@since	01/15/23
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(List<City> arr) {
		int n = arr.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr.get(j).compareTo(arr.get(j + 1)) > 0) {
					swap(arr, j, j + 1);
				}
  			}
		}
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(List<City> arr) {
		int n = arr.size();
		for (int i = 0; i < n-1; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (arr.get(j).compareTo(arr.get(min)) < 0) min = j;
			}
			swap(arr, min, i);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 * 	CURRENTLY SORTING BY NAME
	 */
	public void insertionSort(List<City> arr) {
		int n = arr.size();
        for (int i = 1; i < n; ++i) {
            City cur = arr.get(i);
            int j = i - 1;
			while (j >= 0 && arr.get(j).getName().compareTo(cur.getName()) > 0) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, cur);
        }
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 * 	CURRENTLY SORTING DESCENDING
	 */
	public void mergeSort(List<City> arr, int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);
			merge(arr, l, m, r);
        }
	}
	
	public void merge(List<City> arr, int l, int m, int r) {
		int size1 = m - l + 1;
		int size2 = r - m;
		List<City> left = new ArrayList<City>();
		List<City> right = new ArrayList<City>();
		for (int i = 0; i < size1; ++i) left.add(arr.get(l + i));
		for (int j = 0; j < size2; ++j) right.add(arr.get(m + 1 + j));
		int i = 0, j = 0;
		int k = l;
		while (i < size1 && j < size2) {
			if (left.get(i).compareTo(right.get(j)) > 0) {
				arr.set(k, left.get(i));
				i++;
			}
			else {
				arr.set(k, right.get(j));
				j++;
			}
			k++;
		}
		while (i < size1) {
			arr.set(k, left.get(i));
			i++;
			k++;
		}
		while (j < size2) {
			arr.set(k, right.get(j));
			j++;
			k++;
		}
    }
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(List<City> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %4d", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr.get(a));
			else System.out.printf(", %4d", arr.get(a));
		}
		System.out.println(" )");
	}
	
	/*public void run() {
		int[] arr = new int[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();


		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr, 0, 9);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	}/**/
}
