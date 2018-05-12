package com.lal.demo.coding;

import java.util.HashMap;

public class TwoSum {

	public static int[] findTwoSumOn(int[] list, int sum) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < list.length; i++) {
			int diff = sum - list[i];
			if (map.containsKey(diff) && map.get(diff) != i) {
				return new int[] { i, map.get(diff) };
			} else {
				map.put(list[i], i);
			}
		}

		return null;
	}

	public static int[] findTwoSumOn1(int[] list, int sum) {
		if (list == null || list.length < 2)
			return null;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < list.length; i++) {
			if (map.containsKey(list[i])) {
				return new int[] { map.get(list[i]), i };
			} else {
				map.put(sum - list[i], i);
			}
		}

		return null;
	}

	public static int[] findTwoSumOnSquared(int[] list, int sum) {
		for (int i = 0; i < list.length; i++) {
			for (int j = i + 1; j < list.length; j++) {
				if ((list[i] + list[j]) == sum) {
					return new int[] { i, j };
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		int[] indices = findTwoSumOn(new int[] { 3, 1, 5, 7, 5, 9 }, 16);
		if (indices != null) {
			System.out.println(indices[0] + " " + indices[1]);
		}
	}

}
