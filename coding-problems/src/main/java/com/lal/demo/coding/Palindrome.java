package com.lal.demo.coding;

public class Palindrome {
	public static boolean isPalindrome(String word) {
		if (word != null && !"".equals(word.trim())) { // use StringUtils.isNotBlank in real app
			StringBuffer buffer = new StringBuffer();

			for (int i = word.length(); i > 0; i--) {
				buffer.append(word.charAt(i - 1));
			}

			return word.equalsIgnoreCase(buffer.toString());
		}

		return false;
	}

	public static boolean isPalindromeV2(String word) {
		if (word == null) {
			return false;
		}

		return word.equalsIgnoreCase(new StringBuffer(word).reverse().toString());
	}

	public static void main(String[] args) {
		System.out.println(Palindrome.isPalindrome("Deleveled"));
		System.out.println(Palindrome.isPalindrome("rubber"));
		System.out.println(Palindrome.isPalindrome("rubbur"));
		System.out.println(Palindrome.isPalindrome("lal"));
		System.out.println("V2 now...");
		System.out.println(Palindrome.isPalindromeV2("Deleveled"));
		System.out.println(Palindrome.isPalindromeV2("rubber"));
		System.out.println(Palindrome.isPalindromeV2("rubbur"));
		System.out.println(Palindrome.isPalindromeV2("lal"));
	}
}
