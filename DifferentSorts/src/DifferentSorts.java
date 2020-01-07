//CIT360 - 01, Zachary Brennan; Compares four different sorts with each other.

import java.util.Random;

public class DifferentSorts {
	
	
	public static void main(String[] args) {
		//Variables
		int size = 1000;
		int maxValue = 1000;
		int iterations = 750;
		long estTime = 0;
		long startingTime = 0;
		
		//Arrays
		int[] original = new int[size];
		int[] temp = new int[size];
		//Arrays for the averages
		long[] bubbleAvg = new long[iterations];
		long[] quickAvg = new long[iterations];
		long[] selectionAvg = new long[iterations];
		long[] insertionAvg = new long[iterations];
		
		
		//Randomizing the Array and the iterations 
	for(int i = 0; i <= iterations-1;i++) {
		
		//Randomizing the array with a max value
		randomArray(original,maxValue);
		//Making a copy of the array
		copyArray(original,temp);
		
		//Implementing the sort
	
		//Quick
		startingTime = System.nanoTime();
		quickSort(temp,0,temp.length-1);
		estTime = System.nanoTime() - startingTime;
		quickAvg[i] = estTime;
		
		copyArray(original,temp);
		
		//Selection
		startingTime = System.nanoTime();
		selectionSort(temp);
		estTime = System.nanoTime() - startingTime;
		selectionAvg[i] = estTime;
		
		copyArray(original,temp);
		
		//Insertion
		startingTime = System.nanoTime();
		insertionSort(temp);
		estTime = System.nanoTime() - startingTime;
		insertionAvg[i] = estTime;
		
		copyArray(original,temp);
		
		//Bubble
		startingTime = System.nanoTime();
		bubbleSort(temp);
		estTime = System.nanoTime() - startingTime;
		bubbleAvg[i] = estTime;

		
	}
	
		//Outputs
		System.out.println("Array Size: " +size);
		System.out.println("Interations: " + iterations);
		System.out.println("Max Value: "+ maxValue);
		System.out.println("Average Time(Nano Seconds) \n Bubble Sort: "+ average(bubbleAvg)
						+"\n Quick Sort: "+ average(quickAvg) + "\n Selection Sort: "+average(selectionAvg)
						+ "\n Insertion Sort: " + average(insertionAvg));
	
		
		
	}
	
	/**
	 * This method asks for an int array and an int. 
	 * The array is filled with random values. 
	 * The int represents the max number that will be placed in the array.
	 * @param array
	 * @param max
	 */
	
	public static void randomArray(int[] array, int max) {
		Random rand = new Random();

		for(int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(max);
		}
	}
	/**
	 * This method asks for a long array, its goal is to get the average of all number in the array.
	 * It returns a long that is the average of the array. 
	 * @param array
	 * @return
	 */
	public static long average(long[] array) {
		long sum = 0;
		long avg = 0;
		for(int i = 0; i < array.length -1; i++) {
			sum += array[i];
		}
		avg = sum / array.length;
		return avg;
	}
	
	/** 
	 * This method asks for two int arrays. 
	 * It is in place to copy the first array into the second array. 
	 * @param a1
	 * @param a2
	 */
	public static void copyArray(int[] a1, int[] a2) {
		
		for(int i = 0; i < a1.length;i++) {
			a2[i] = a1[i];
		}
	}
	
	/**
	 * This method asks for an int array.
	 *  It sorts the array by pushing one number to the top, and then repeating.
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		int count = 0;
		boolean swapped = true;
		int temp = 0;
		while(swapped == true) {
			swapped = false; 
			for(int i = 0; i < array.length - 1 - count; i++) {
				if(array[i] > array[i+1]) {
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp; 
					swapped = true;
				}
			}
			count++;
		}
	}
	
	/**
	 * This method asks for an int array and 2 ints.
	 * One int is the starting placement, the other is the ending.
	 * This sort uses recursion to go through half of the sort at a time. 
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void quickSort(int array[], int start, int end) {
		int mid = (start+end)/2;
		int pivot = array[mid];
		int i = start; 
		int j = end;
		int temp = 0; 
		while(i <= j) {
			while(array[i] < pivot) i++;
				while (array[j] > pivot) j--;
				if(i <= j) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp; 
					i++;
					j--;
				}
			}

		if(start < j) {
			quickSort(array,start,j);
		}
		if(i < end) {
			quickSort(array,i,end);
		}
	}

	/**
	 * This method asks for an int array.
	 * It switches every number individually with each other. 
	 * @param array
	 */
	public static void selectionSort(int[] array){
		int smallest = 0;
		int pos = 0;
		int temp = 0;
		for(int i = 0; i < array.length -1; i++) {
			smallest = array[i];
			pos = i;
			for(int j = i+1; j < array.length; j++){
				if(array[j] < smallest) {
					smallest = array[j];
					pos = j;
				}
 			}
			temp = array[i];
			array[i] = smallest;
			array[pos] = temp;
		}
	}
	
	/**
	 * This method asks for one int array to sort.
	 * This sort compares a key with a number before it and switches when needed.
	 * @param array
	 */
	public static void insertionSort(int[] array) {
		int a = array.length;
		for(int z = 1; z < a; z++){
			int c = z - 1;
			int key = array[z];
			while((c>-1) && (array[c] > key)) {
				array[c+1] = array[c];
				c--;
			}
			array[c+1] = key;
		}
	}
}	