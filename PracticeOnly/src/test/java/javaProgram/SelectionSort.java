package javaProgram;

import java.util.Arrays;
import java.util.Random;

public class SelectionSort {

	public static void main(String[] args) {
		int [] number = new int[10];
		Random random=new Random();
		for(int i=0;i<number.length;i++) {
			number[i]=random.nextInt(1000);
		}
		
		System.out.println(Arrays.toString(number));
		long startTime = System.currentTimeMillis();
		selectionSort(number);
		long endTime = System.currentTimeMillis();
		System.out.println("Took "+(endTime-startTime)+"ms");
		System.out.println(Arrays.toString(number));
		

	}

	private static void selectionSort(int[] number) {
		int length=number.length;
		
		for(int i =0; i<length-1;i++) {
			int min=number[i];
			int index=i;
			for(int j = i+1;j<length;j++) {
				if(number[j]<min) {
					min=number[j];
					index=j;
				}
			}
			swap(number,i,index);
		}
		
	}

	private static void swap(int[] number, int a, int b) {
		int temp = number[a];
		number[a]=number[b];
		number[b]=temp;
		
	}

}
