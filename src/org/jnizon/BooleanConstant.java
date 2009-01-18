package org.jnizon;

public class BooleanConstant extends Constant {
	private boolean value;
	
	public BooleanConstant(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		if (value == true) return "True";
		else return "False";
	}
	
	@Override
	public boolean equals(Expression expr) {
		if (!(expr instanceof BooleanConstant)) return false;
		return ((BooleanConstant)expr).getValue() == this.value;
	}

	@Override
	public Expression evaluate(Context ctx) {
		return this;
	}

	@Override
	public Expression getChild(int index) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	public static Expression parseBool(String text) {
		if (text.equals("True")) return new BooleanConstant(true);
		else if (text.equals("False")) return new BooleanConstant(false);
		else return new NullExpression();
	}
}
