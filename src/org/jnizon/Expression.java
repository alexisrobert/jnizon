package org.jnizon;

public interface Expression {
	
	public Expression evaluate(Context ctx);
	
	public int getChildCount();
	public Expression getChild(int index);
	
}
