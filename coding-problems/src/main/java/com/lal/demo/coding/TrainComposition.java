package com.lal.demo.coding;

public class TrainComposition {

	private Wagon leftMostWagon;
	private Wagon rightMostWagon;

	public void attachWagonFromLeft(int wagonId) {
		Wagon wagon = new Wagon(wagonId);
		if (this.leftMostWagon == null) {
			this.leftMostWagon = this.rightMostWagon = wagon;
		} else {
			this.leftMostWagon.left = wagon;
			wagon.right = this.leftMostWagon;
			this.leftMostWagon = wagon;
		}
	}

	public void attachWagonFromRight(int wagonId) {
		Wagon wagon = new Wagon(wagonId);

		if (this.rightMostWagon == null) {
			this.leftMostWagon = this.rightMostWagon = wagon;
		} else {
			this.rightMostWagon.right = wagon;
			wagon.left = this.rightMostWagon;
			this.rightMostWagon = wagon;
		}
	}

	public int detachWagonFromLeft() {
		if (this.leftMostWagon == null) {
			return -1;
		}
		int wagonId = this.leftMostWagon.wagonId;
		Wagon wagon = this.leftMostWagon.right;
		if (wagon != null) {
			wagon.left = null;
		}
		this.leftMostWagon = null;
		this.leftMostWagon = wagon;

		return wagonId;
	}

	public int detachWagonFromRight() {
		if (this.rightMostWagon == null) {
			return -1;
		}
		int wagonId = this.rightMostWagon.wagonId;
		Wagon wagon = this.rightMostWagon.left;
		if (wagon != null) {
			wagon.right = null;
		}
		this.rightMostWagon = null;
		this.rightMostWagon = wagon;

		return wagonId;
	}

	public static void main(String[] args) {
		TrainComposition tree = new TrainComposition();
		tree.attachWagonFromLeft(7);
		tree.attachWagonFromLeft(13);
		System.out.println(tree.detachWagonFromRight()); // 7
		System.out.println(tree.detachWagonFromLeft()); // 13
	}

	private static class Wagon {
		private int wagonId;
		private Wagon left;
		private Wagon right;

		public Wagon(int wagonId) {
			this.wagonId = wagonId;
		}
	}

}