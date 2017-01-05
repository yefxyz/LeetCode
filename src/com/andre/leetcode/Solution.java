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

	/**
	 * 倒转一个整数，若溢出整数范围，返回0。
	 * 
	 * @param x
	 * @return
	 */
	public static int reverseInt(int x) {
		long result = 0;

		int temp = x;
		while (Math.abs(temp) > 0) {
			result = result * 10 + temp % 10;
			temp = temp / 10;
		}

		if (Math.abs(result) > Integer.MAX_VALUE) {
			result = 0;
		}
		return (int) result;
	}

	public static int reverseInt2(int x) {
		int result = 0;
		int last = 0;
		while (Math.abs(x) > 0) {
			last = result;
			result = result * 10 + x % 10;
			x = x / 10;
		}
		// 只需要判断最后结果和上一次即可知道是否溢出。
		if (result / 10 != last) {
			return 0;
		}
		return result;
	}

	/**
	 * 字符串转整数。
	 * 
	 * @param str
	 * @return
	 */
	public static int myAtoi(String str) {
		int n = str.length();
		int result = 0;
		int last = 0;
		int sign = 0;
		int idx = 0;
		if (n == 0) {
			return 0;
		}
		// 起始空格与正负号的处理放到for循环外面，提高速度。
		while (str.charAt(idx) == ' ') {
			idx++;
		}
		if (str.charAt(idx) == '+' || str.charAt(idx) == '-') {
			sign = str.charAt(idx) == '+' ? 1 : -1;
			idx++;
		}
		for (int i = idx; i < n; i++) {
			char c = str.charAt(i);
			if (i >= 0 && c >= '0' && c <= '9') {
				last = result;
				result = result * 10 + (c - '0');
			} else {
				break;
			}
		}
		if (sign == 0) {
			sign = 1;
		}
		if (result / 10 != last) {
			return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		return sign * result;
	}

	/**
	 * 判断是否为回文数。
	 * 
	 * @param x
	 * @return
	 */
	public static boolean isPalindrome(int x) {
		if (x < 0 || x % 10 == 0) {
			return false;
		}
		if (x < 10) {
			return true;
		}
		int rev = 0;
		while (x > rev) {
			rev = rev * 10 + x % 10;
			x = x / 10;
		}
		return x == rev || x == rev / 10;
	}

	/**
	 * 正则表达式匹配。支持 '.' 和 '*' 。
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean isRegExpMatch(String s, String p) {
		int m = s.length(), n = p.length();
		// dp[m][n] 代表 s 匹配 p。
		boolean[][] dp = new boolean[m + 1][n + 1];
		// 空串相互匹配。
		dp[0][0] = true;
		// 状态转移。
		for (int i = 0; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// 如果模式字符不是 '*' 。
				if (p.charAt(j - 1) != '*') {
					dp[i][j] =
							// 字符相同或模式字符为 '.' 。
							i > 0 && dp[i - 1][j - 1] && (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.');
				}
				// 如果模式字符是 '*' 。
				else {
					dp[i][j] =
							// 模式 ?* 只重复0次的情况。
							dp[i][j - 2]
									// 模式 ?* 至少重复1次。
									|| (i > 0 && dp[i - 1][j] && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.'));
				}
			}
		}
		return dp[m][n];
	}

	public static int maxArea(int[] h) {
		// 效率太低。
		// int n = h.length;
		// int[][] f = new int[n][n];
		// f[0][1] = Math.min(h[0], h[1]);
		// int ma = f[0][1];
		// for (int i = 0; i < n; i++) {
		// for (int j = i + 1; j < n; j++) {
		// int a = (j - i) * Math.min(h[i], h[j]);
		// ma = Math.max(a, ma);
		// }
		// }
		// return ma;

		int n = h.length;
		int l = 0, r = n - 1;
		int m = 0;
		while (l < r) {
			m = Math.max(m, (r - l) * Math.min(h[l], h[r]));
			if (h[l] < h[r]) {
				l++;
			} else {
				r--;
			}
		}
		return m;
	}

	public static String intToRoman(int n) {
		StringBuilder sb = new StringBuilder("");
		char[] roman = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
		int i = roman.length - 1;
		while (n > 0) {
			int div1 = romanDivisor(i);
			int div2 = romanDivisor(i - 1);
			int div3 = romanDivisor(i - 2);
			int x = n / div1, y = n / div2;
			if (x <= 0 && y <= 0) {
				i--;
				continue;
			}
			if (x > 0) {
				int z = n % div1 / div2;
				if (z > 3) {
					sb.append(roman[i - 1]).append(roman[i + 1]);
					n = n % div2;
				} else {
					sb = appendRoman(sb, x, roman[i]);
					n = n % div1;
				}
				i--;
			} else if (y > 3) {
				sb.append(roman[i - 1]).append(roman[i]);
				n = n % div2;
				i--;
			} else {
				int z = n % div2 / div3;
				if (z > 3) {
					sb.append(roman[i - 2]).append(roman[i]);
					n = n % div3;
					i = i - 2;
				} else {
					sb = appendRoman(sb, y, roman[i - 1]);
					n = n % div2;
					i--;
				}
			}
		}
		return sb.toString();
	}
	private static StringBuilder appendRoman(StringBuilder s, int n, char c) {
		for (int i = 0; i < n; i++) {
			s.append(c);
		}
		return s;
	}
	private static int romanDivisor(int i) {
		int p1 = i / 2;
		int p2 = (int) Math.ceil(i / 2D);
		return (int) (Math.pow(2, p1) * Math.pow(5, p2));
	}

	public static String intToRoman2(int num) {
		String M[] = {"", "M", "MM", "MMM"};
		String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
	}

	public static String intToRoman3(int num) {
		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			while (num >= values[i]) {
				num -= values[i];
				sb.append(strs[i]);
			}
		}
		return sb.toString();
	}
}
