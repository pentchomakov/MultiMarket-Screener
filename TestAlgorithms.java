
public class TestAlgorithms {
public static int [] a = new int [1000];

	public static void randomizer(){
		for (int i = 0 ; i < 1000 ; i++) { 
			a[i] = (int) (Math.random () * 1000); 
		}
	}

	public static void main(String[] args) {
		randomizer();
		
		//System.out.println("Insertion Sort, it works as following: ");
		//printer(intInsertionSort(a));
		//System.out.println("Selection Sort, it works as following: ");
	    //printer(selectionSort(a));
		//System.out.println("Bubble Sort, it works as following: ");
		//printer(bubbleSort(a));
		//System.out.println("Shell Sort, it works as following: ");
		//printer(shellSort(a));
		System.out.println("QuickSort, it works as following: ");
		printer(sort(a));
		//System.out.println("MergeSort, it works as following: ");
		//printer(mergeSort(a));

	}
	
	//**
	//**
	//InsertionSort
	//**
	//**
	public static int[] intInsertionSort(int [] a) {
		for(int i = 1; i < a.length; i++) {
			int temp = a[i];
			int j;
			
			for(j = i -1; j >= 0 && temp < a[j]; j--){
				a[j + 1] = a[j];
			}
			
			a[j + 1] = temp;
		}
		return a;
	}
	
	//**
	//**
	//SelectionSort
	//**
	//**
	
	public static int[] selectionSort(int [] a) {
		int i, j, first, temp;
		for(i = a.length -1; i > 0; i--) {
			first = 0;
			for(j = 1; j <= i; j++) {
				if(a[i] < a[first]){
					first = j;
				}
			}
			temp = a[first];
			a[first] = a[i];
			a[i] = temp;
		}
		return a;
	}
	
	//**
	//**
	//BubbleSort
	//**
	//**
	
	public static int [] bubbleSort(int [] a) {
		int j;
		boolean flag = true;
		int temp;
		
		while(flag) {
			flag = false;
			for(j = 0; j < a.length -1; j++) {
				if(a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					flag = true;
				}
			}
		}
		return a;
	}
	
	//**
	//**
	//ShellSort
	//**
	//**
	
	public static int [] shellSort(int [] a) {
		int increment = a.length / 2;
		while(increment > 0) {
			for(int i = increment; i < a.length; i++) {
				int j = i;
				int temp = a[i];
				
				while(j >= increment && a[j - increment] > temp) {
					a[j] = 	a[j - increment];
					j = j - increment;
				}
				a[j] = temp;
			}
			if(increment == 2) {
				increment = 1;
			}
			else {
				increment *= (5.0 / 11);
			}
		}
		return a;
	}
	
	//**
	//**
	// Quicksort 
	// Time complexity
	//**
	//**
	
	public static int [] sort(int [] a) {
		int left = 0;
		int right = a.length-1;
		
		quickSort(left, right);
		return a;
	}
	
	public static void quickSort(int left, int right) {
		if(left >= right){
			return;
		}
		
		int pivot = a[right];
		int partition = partition (left, right, pivot); 
		quickSort(0, partition -1);
		quickSort(partition + 1, right);
	}
	
	public static int partition(int left, int right, int pivot) {
		int leftCursor = left - 1;
		int rightCursor = right;
		while(leftCursor < rightCursor) {
				while(a[++leftCursor] < pivot);
				while(rightCursor > 0 && a[--rightCursor] > pivot);
			if(leftCursor >= rightCursor) {
				break;
			}
			else {
				swap(leftCursor, rightCursor);
			}
		}
		swap(leftCursor, right);
		return leftCursor;
	}
	
	public static void swap(int left, int right) {
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}
	
	//**
	//**
	//MergeSort
	//Worst Case Big(O) = (n*log(n))
	//**
	//**
	
	public static int[] mergeSort(int [] a) {
		if(a.length <= 1) {
			return a;
		}
		
		int[] first = new int[a.length/2];
		int[] second = new int[a.length - first.length];
		
		System.arraycopy(a, 0, first, 0, first.length);
		System.arraycopy(a, first.length, second, 0, second.length);
		
		
		mergeSort(first);
		mergeSort(second);
		
		merge(first, second, a);
		return a;
	}
	
	private static void merge(int [] first, int[] second, int[] result) {
		int iFirst = 0;
		int iSecond = 0;
		int j = 0;
		
		while(iFirst < first.length && iSecond < second.length) {
			if(first[iFirst] < second[iSecond]) {
				result[j] = first[iFirst];
				iFirst++;
			}
			else {
				result[j] = second[iSecond];
				iSecond++;
			}
			j++;
		}
		System.arraycopy(first, iFirst, result, j, first.length - iFirst);
		System.arraycopy(second, iSecond, result, j, second.length - iSecond);
	}

	
	public static void printer(int [] a){
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}
}
