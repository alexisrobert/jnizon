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
}
