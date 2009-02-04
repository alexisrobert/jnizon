package org.jnizon.core;

public interface Expression {

	public Expression evaluate(Context ctx);

	public Expression getHead();

	public int getChildCount();

	public Expression getChild(int index);

	public boolean equals(Expression expr);
}
