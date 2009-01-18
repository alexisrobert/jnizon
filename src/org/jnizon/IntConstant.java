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
		return Integer.toString(value);
	}
	
	@Override
	public Expression getChild(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getChildCount() {
		return 0;
	}
}

