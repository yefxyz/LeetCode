package com.andre.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

	/**
	 * 给定一个整数数组和一个目标整数，若数组中有两个元素加和等于目标，返回这两个元素的下标。
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		return result;
	}

	public static int[] twoSumWithMap(int[] nums, int target) {
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				result[1] = i;
				result[0] = map.get(target - nums[i]);
				break;
			}
			map.put(nums[i], i);
		}
		return result;
	}

	/**
	 * 用一个链表结构表示一个非负整数，将两个数相加。
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		addNode(head, l1 != null ? l1 : null, l2 != null ? l2 : null, 0);
		return head.next;
	}

	private static void addNode(ListNode last, ListNode l1, ListNode l2, int carry) {
		int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
		if (l1 == null && l2 == null && sum == 0) {
			return;
		} else {
			last.next = new ListNode(sum % 10);
			addNode(last.next, l1 != null ? l1.next : null, l2 != null ? l2.next : null, sum / 10);
		}
	}

	/**
	 * 求字符串中无重复字母的最长子串长度。
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s) {
		int result = 0;
		String sub = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int idx = sub.indexOf(c);
			if (idx == -1) {
				sub = sub + c;
			} else {
				if (result < sub.length()) {
					result = sub.length();
				}
				sub = sub.substring(idx + 1) + c;
			}
		}
		return result < sub.length() ? sub.length() : result;
	}

	public static int lengthOfLongestSubstring2(String s) {
		int lls = 0;
		String ls = "";
		int i = 0;
		while (i < s.length()) {
			int idx = ls.indexOf(s.charAt(i));
			if (idx == -1) {
				ls = ls + s.charAt(i);
			} else {
				ls = ls.substring(idx + 1) + s.charAt(i);
			}
			lls = Math.max(lls, ls.length());
			i++;
		}
		return lls;
	}

	public static int lengthOfLongestSubstring3(String s) {
		int lls = 0;
		int[] map = new int[256];
		Arrays.fill(map, -1);
		for (int i = 0, j = 0; i < s.length(); i++) {
			int c = s.charAt(i);
			j = Math.max(j, map[c] + 1);
			map[c] = i;
			lls = Math.max(lls, i - j + 1);
		}
		return lls;
	}

	/**
	 * 求两个有序整数数组所有元素的中位数。
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int[] arr = Arrays.copyOf(nums1, nums1.length + nums2.length);
		System.arraycopy(nums2, 0, arr, nums1.length, nums2.length);
		doHandleArray(arr, nums1.length);
		int idx1 = arr.length / 2;
		int idx2 = idx1 - 1 + arr.length % 2;
		return (double) (arr[idx1] + arr[idx2]) / 2;
	}

	private static void doHandleArray(int[] arr, int idx) {
		if (idx == 0 || idx == arr.length || arr[idx - 1] <= arr[idx]) {
			return;
		}
		int axis = arr[idx - 1];
		int i = idx;
		while (i < arr.length && arr[i] < axis) {
			arr[i - 1] = arr[i++];
		}
		arr[i - 1] = axis;
		doHandleArray(arr, idx - 1);
	}

	/**
	 * 算法思路参见Evernote中的说明。
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		if (m > n) {
			int temp = m;
			m = n;
			n = temp;
			int[] tempArr = nums1;
			nums1 = nums2;
			nums2 = tempArr;
		}

		int iMin = 0;
		int iMax = m;
		while (iMin <= iMax) {
			int i = (iMin + iMax) / 2;
			int j = (m + n + 1) / 2 - i;

			if (i < m && nums2[j - 1] > nums1[i]) {
				// i 值太小。
				iMin = iMin + 1;
			} else if (i > 0 && nums1[i - 1] > nums2[j]) {
				// i 值太大。
				iMax = i - 1;
			} else {
				// i 值正好，命中。

				int maxL = 0;
				int minR = 0;

				if (i == 0) {
					maxL = nums2[j - 1];
				} else if (j == 0) {
					maxL = nums1[i - 1];
				} else {
					maxL = Math.max(nums1[i - 1], nums2[j - 1]);
				}

				if ((m + n) % 2 == 1) {
					return maxL;
				}

				if (i == m) {
					minR = nums2[j];
				} else if (j == n) {
					minR = nums1[i];
				} else {
					minR = Math.min(nums1[i], nums2[j]);
				}

				return (double) (maxL + minR) / 2;
			}
		}

		return -1;
	}

	/**
	 * 最长回文子串。
	 * 
	 * @param s
	 * @return
	 */
	static Map<Integer, String> longestPalindrome_map = new HashMap<>();
	public static String longestPalindrome(String s) {
		int len = s.length();
		if (len == 1) {
			return s;
		}
		for (int n = len; n >= 1; n--) {
			if (longestPalindrome_map.containsKey(n)) {
				return longestPalindrome_map.get(n);
			}
			for (int i = 0; i < len - n + 1; i++) {
				String sub = s.substring(i, i + n);
				if (checkPalindrome(sub, longestPalindrome_map)) {
					return sub;
				}
			}
		}
		return "";
	}

	private static boolean checkPalindrome(String s, Map<Integer, String> map) {
		int len = s.length();
		if (len == 1) {
			return true;
		}
		for (int i = len / 2 - 1; i >= 0; i--) {
			if (s.charAt(i) != s.charAt(len - i - 1)) {
				return false;
			} else {
				map.put(len - 2 * i, s.substring(i, len - i));
			}
		}
		return true;
	}

	static int longestPalindrome_n = 0;
	static int longestPalindrome_i = 0;
	public static String longestPalindrome2(String s) {
		int len = s.length();
		if (len == 1) {
			return s;
		}
		for (int i = 0; i < len - 1; i++) {
			// 假设最长子串长度为奇数。
			extendPalindrome(s, i, i);
			// 假设最长子串长度为偶数。
			extendPalindrome(s, i, i + 1);
		}
		return s.substring(longestPalindrome_i, longestPalindrome_i + longestPalindrome_n);
	}

	private static void extendPalindrome(String s, int i, int j) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}
		if (longestPalindrome_n < j - i - 1) {
			longestPalindrome_n = j - i - 1;
			longestPalindrome_i = i + 1;
		}
	}

	/**
	 * 字符串锯齿（Zigzag）转折编码。
	 * 
	 * @param s
	 * @param rows
	 * @return
	 */
	public static String zigzagConvert(String s, int rows) {
		int len = s.length();
		// rows + rows - 2，即2 * (rows - 1)个字母会占据1 + rows - 2 = rows - 1列。
		int zz = rows + rows - 2;
		if (len <= rows || zz == 0) {
			return s;
		}
		// int left = len % zz;
		// int cols = len / zz * (rows - 1) + (left <= rows ? 1 : 1 + left - rows);
		// Character[][] matrix = new Character[rows][cols];

		StringBuilder[] sbArr = new StringBuilder[rows];
		for (int i = 0; i < sbArr.length; i++) {
			sbArr[i] = new StringBuilder();
		}
		for (int i = 0; i < len; i++) {
			// int x1 = i / zz;
			int x2 = i % zz;
			int m = x2 < rows ? x2 : zz - x2;
			// int n = x2 < rows ? x1 * (rows - 1) : x1 * (rows - 1) + x2 - rows + 1;
			// matrix[m][n] = s.charAt(i);
			sbArr[m].append(s.charAt(i));
		}

		StringBuilder resSB = new StringBuilder();
		for (StringBuilder sb : sbArr) {
			resSB.append(sb);
		}
		return resSB.toString();
	}

}
