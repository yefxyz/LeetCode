package com.andre.leetcode;

public class Constants {

	public static final int[] TWO_SUM_SOURCE_NUMBERS = {2, 4, 7, 9, 11, 15, 18, 21};

	public static final ListNode ADD_TWO_NUMBER_01 = new ListNode(2);
	static {
		ADD_TWO_NUMBER_01.next = new ListNode(4);
		ADD_TWO_NUMBER_01.next.next = new ListNode(3);
	}

	public static final ListNode ADD_TWO_NUMBER_02 = new ListNode(5);
	static {
		ADD_TWO_NUMBER_02.next = new ListNode(6);
		ADD_TWO_NUMBER_02.next.next = new ListNode(4);
	}

	public static final String LONGEST_SUBSTRING_CASE = "pwwkew";

	public static final int[] MEDIAN_OF_TWO_ARRAYS_01 = {2, 6, 7, 9, 10};
	public static final int[] MEDIAN_OF_TWO_ARRAYS_02 = {1, 3, 4, 5, 8};
}
