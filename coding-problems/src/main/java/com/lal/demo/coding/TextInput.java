package com.lal.demo.coding;

public class TextInput {

	private StringBuffer value = new StringBuffer();

	public void add(char c) {
		value.append(c);
	}

	public String getValue() {
		return value.toString();
	}

}
