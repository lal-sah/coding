package com.lal.demo.coding;

public class SortedSearch {

	public static int binarySearch(int[] sortedArray, int lessThan, int l, int r) {
		if (r >= l) {
			int mid = l + (r - 1) / 2;

			if (sortedArray[mid] == lessThan) {
				return mid;
			} else if (lessThan > sortedArray[mid] && lessThan <= sortedArray[mid + 1]) {
				return mid + 1;
			}

			if (sortedArray[mid] > lessThan) {
				return binarySearch(sortedArray, lessThan, l, mid - 1);
			}

			return binarySearch(sortedArray, lessThan, mid + 1, r);
		}

		return -1;
	}

	public static int countNumbers(int[] sortedArray, int lessThan) {
		if (lessThan <= sortedArray[0]) {
			return 0;
		} else if (lessThan > sortedArray[sortedArray.length - 1]) {
			return sortedArray.length;
		} else {
			return binarySearch(sortedArray, lessThan, 0, sortedArray.length - 1);
		}
	}

	public static void main(String[] args) {
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 0));
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 1));
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 2));
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 3));
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 4));
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 5));
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 6));
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 7));
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 8));
		System.out.println(countNumbers(new int[] { 1, 3, 5, 7 }, 9));
	}
}
