package org.jnizon;

public class Assignment implements Expression {
	
	private Identifier variable;
	private Expression value;
	
	public Assignment(Identifier variable, Expression value) {
		this.variable = variable;
		this.value = value;
	}
	
	@Override
	public Expression evaluate(Context ctx) {
		Expression result = value.evaluate(ctx);
		ctx.put(variable, result);
		return result;
	}
	
	@Override
	public int getChildCount() {
		return 1;
	}
	
	@Override
	public Expression getChild(int index) {
		if(index == 0) return value;
		throw new RuntimeException("Out of bounds");
	}
}
