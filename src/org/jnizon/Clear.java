package org.jnizon;

public class Clear implements Expression {
	private Identifier variable;
	
	public Clear(Identifier variable) {
		this.variable = variable;
	}
	
	@Override
	public Expression evaluate(Context ctx) {
		if (ctx.containsKey(variable))
			ctx.remove(variable);
		
		return null;
	}

}
