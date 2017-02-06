package com.andre.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
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

}
