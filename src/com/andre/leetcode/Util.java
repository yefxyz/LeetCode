package com.andre.leetcode;

import java.util.Arrays;

public class Util {

	public static int[] intArrConcat(int[] arr1, int[] arr2) {
		int[] result = Arrays.copyOf(arr1, arr1.length + arr2.length);
		System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
		return result;
	}
	
	
}
