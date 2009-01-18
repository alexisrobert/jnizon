package org.jnizon;

public class NullExpression implements Expression {

	@Override
	public Expression evaluate(Context ctx) {
		return this;
	}

	@Override
	public Expression getChild(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Expression expr) {
		return expr instanceof NullExpression;
	}

}
