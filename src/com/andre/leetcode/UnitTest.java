package com.andre.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class UnitTest {

	// @Test
	public void testTwoSum() {
		int runTimes = 100_000;
		long start = System.nanoTime();
		for (int i = 0; i < runTimes; i++) {
			int[] twoSumArr = Solution.twoSum(Constants.TWO_SUM_SOURCE_NUMBERS, 18);
			// int[] twoSumArr = Solution.twoSumWithMap(Constants.TWO_SUM_SOURCE_NUMBERS, 18);
		}
		long duration = (System.nanoTime() - start) / runTimes;
		System.out.println("Two Sum performance: " + duration + " nanoseconds.");
	}

	// @Test
	public void testAddTwoNumbers() {
		ListNode result = Solution.addTwoNumbers(Constants.ADD_TWO_NUMBER_01, Constants.ADD_TWO_NUMBER_02);
		Assert.assertNotNull(result);

		int nRes = 0;
		int digit = 0;
		ListNode iterNode = result;
		while (iterNode != null) {
			nRes = nRes + iterNode.val * (int) Math.pow(10, digit++);
			iterNode = iterNode.next;
		}
		System.out.println("Add two numbers: " + nRes);
	}

	// @Test
	public void testLongestSubstring() {
		// int result = Solution.lengthOfLongestSubstring(Constants.LONGEST_SUBSTRING_CASE);
		int result = Solution.lengthOfLongestSubstring2(Constants.LONGEST_SUBSTRING_CASE);
		System.out
				.println("The longest substring of \"" + Constants.LONGEST_SUBSTRING_CASE + "\" length is : " + result);
	}

	// @Test
	public void testMedianOfSortedArrays() {
		double result = Solution.findMedianSortedArrays2(Constants.MEDIAN_OF_TWO_ARRAYS_01,
				Constants.MEDIAN_OF_TWO_ARRAYS_02);
		System.out.println("Median: " + result);
	}

	@Test
	public void testLongestPalindrome() {
		String s = Solution.longestPalindrome2(Constants.LONGEST_PALINDROME_CASE);
		System.out.println(s);
	}

}
