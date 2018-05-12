package com.lal.demo.coding;

public class NumericInput extends TextInput {

	@Override
	public void add(char c) {
		if (c >= '0' && c <= '9') {
			super.add(c);
		} else {
			System.out.println("Invalid input character (" + c + ") was deteced and ignored!");
		}
	}

	public static void main(String[] args) {
		TextInput input = new NumericInput();
		input.add('1');
		input.add('a');
		input.add('0');
		System.out.println(input.getValue());
	}
}
