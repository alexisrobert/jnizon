package org.jnizon;

public class IntConstant extends Constant {
	
	private int value;
	
	public IntConstant(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public Expression evaluate(Context ctx) {
		return this;
	}
	
	@Override
	public String toString() {
		return "org.jnizon.IntConstant[value:" + value + "]";
	}
}

