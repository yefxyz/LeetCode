package com.andre.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	/**
	 * ����һ�����������һ��Ŀ����������������������Ԫ�ؼӺ͵���Ŀ�꣬����������Ԫ�ص��±ꡣ
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
	 * ��һ������ṹ��ʾһ���Ǹ�����������������ӡ�
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

	public static void addNode(ListNode last, ListNode l1, ListNode l2, int carry) {
		int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
		if (l1 == null && l2 == null && sum == 0) {
			return;
		} else {
			last.next = new ListNode(sum % 10);
			addNode(last.next, l1 != null ? l1.next : null, l2 != null ? l2.next : null, sum / 10);
		}
	}

}
