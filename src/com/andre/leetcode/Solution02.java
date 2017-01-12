package com.andre.leetcode;

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

}
