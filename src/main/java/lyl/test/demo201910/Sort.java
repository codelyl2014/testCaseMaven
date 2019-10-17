package lyl.test.demo201910;

import com.google.gson.Gson;

public class Sort {
	
	public static void bubbleSort(int[] array) {
		int length = array.length;
		for(int i = 0 ; i < length;i++) {
			for(int j = 0;j < length-i-1;j++) {
				if(array[j] > array[j+1]) {
					int tem = array[j];
					array[j] = array[j+1];
					array[j+1] = tem;
				}
			}
		}
	}
	
	
	private static void quickSort(int[] array,int start,int end) {	
        if (array.length < 0){
            return ;
        }
        if (start >= end){
            return ;
        }
		int i = start;
		int j = end;
		int a = array[i];
		while(i < j) {
			while ((i < j) && array[j] > a) {
				j --;
			}
			array[i] = array[j];
			while ((i < j) && array[i] < a) {
				i ++;			
			}			
			array[j] = array[i];
		}
		array[i] = a;
		quickSort(array, start, i-1);
		quickSort(array, i+1, end);
	} 
	
	private static void QKSourt(int[] a, int start, int end) {
        if (a.length < 0){
            return ;
        }
        if (start >= end){
            return ;
        }
        int left = start;
        int right = end;
        int temp = a[left];
        while (left < right){
            while (left < right && a[right] > temp){
                right -- ;
            }
            a[left] = a[right];
            while (left < right && a[left] < temp){
                left ++ ;
            }
            a[right] = a[left];
        }
        a[left] = temp;
        QKSourt(a, start, left -1);
        QKSourt(a,left+1,end);
    }
	
	
	public static void main(String[] args) {
		int[] array = {7,3,9,6,2,8};
		quickSort(array,0,array.length - 1);
		System.out.println(new Gson().toJson(array));
	}

}
