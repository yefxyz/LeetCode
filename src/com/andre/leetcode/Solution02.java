package com.andre.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution02 {

	/**
	 * 获取一组字符串中的最长公共前缀。
	 * 
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix(String[] strs) {
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			prefix = commonPrefix(prefix, strs[i]);
		}
		return prefix;
	}
	private static String commonPrefix(String s1, String s2) {
		if (s1.length() > s2.length()) {
			String tmp = s2;
			s2 = s1;
			s1 = tmp;
		}
		if (s1.length() == 0) {
			return s1;
		}
		int i = 0;
		while (i < s1.length() && s1.charAt(i) == s2.charAt(i)) {
			i++;
		}
		return s1.substring(0, i);
	}

	public static String longestCommonPrefix2(String[] strs) {
		return lcp(strs, strs.length);
	}
	private static String lcp(String[] strs, int n) {
		if (n == 0) {
			return "";
		} else if (n == 1) {
			return strs[0];
		} else {
			return commonPrefix(strs[n - 1], lcp(strs, n - 1));
		}
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		// 首先排序。
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				// 跳过相同的数字，已处理过。
				continue;
			}
			int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
			while (lo < hi) {
				if (nums[lo] + nums[hi] == sum) {
					res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
					while (lo < hi && nums[lo] == nums[lo + 1])
						lo++;
					while (lo < hi && nums[hi] == nums[hi - 1])
						hi--;
					lo++;
					hi--;
				} else if (nums[lo] + nums[hi] < sum)
					lo++;
				else
					hi--;
			}
		}
		return res;
	}

	/**
	 * 加和最接近目标的三元整数组。
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int threeSumClosest(int[] nums, int target) {
		int closest = Integer.MAX_VALUE;
		// 首先排序。
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				// 跳过相同的数字，已处理过。
				continue;
			}
			int lo = i + 1, hi = nums.length - 1;
			while (lo < hi) {
				int sum = nums[i] + nums[lo] + nums[hi];
				if (closest == Integer.MAX_VALUE)
					closest = sum;
				if (sum == target) {
					return sum;
				} else if (sum < target) {
					while (lo < hi && nums[lo] == nums[lo + 1])
						lo++;
					lo++;
				} else {
					while (lo < hi && nums[hi] == nums[hi - 1])
						hi--;
					hi--;
				}
				if (Math.abs(sum - target) < Math.abs(closest - target))
					closest = sum;
			}
		}
		return closest;
	}

	public static List<String> letterCombinations(String digits) {
		String[] chars = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		List<String> digitChars = new ArrayList<>();
		for (int i = 0; i < digits.length(); i++) {
			int idx = digits.charAt(i) - '0';
			if (idx > 1)
				digitChars.add(chars[idx]);
		}
		return letterCombinationsHelper(digitChars);
	}
	private static List<String> letterCombinationsHelper(List<String> digitChars) {
		int n = digitChars.size();
		if (n == 0) {
			return digitChars;
		}
		if (n == 1) {
			List<String> result = new ArrayList<>();
			for (int i = 0; i < digitChars.get(0).length(); i++)
				result.add(digitChars.get(0).substring(i, i + 1));
			return result;
		}
		List<String> result = new ArrayList<>();
		List<String> list = letterCombinationsHelper(digitChars.subList(0, n - 1));
		list.parallelStream().forEach(s -> {
			for (int i = 0; i < digitChars.get(n - 1).length(); i++) {
				StringBuffer sbuf = new StringBuffer(s);
				sbuf.append(digitChars.get(n - 1).charAt(i));
				result.add(sbuf.toString());
			}
		});
		return result;
	}

	public static List<String> letterCombinations2(String digits) {
		// 采用队列方式。
		LinkedList<String> ans = new LinkedList<String>();
		String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		if (digits.length() == 0) {
			return ans;
		}
		ans.add("");
		int len = 0;
		for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			if (x > 1) {
				while (ans.peek().length() == len) {
					String s = ans.remove();
					for (char c : mapping[x].toCharArray())
						ans.add(s + c);
				}
				len++;
			}
		}
		return ans;
	}

}
