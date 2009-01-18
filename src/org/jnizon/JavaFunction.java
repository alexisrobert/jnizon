package org.jnizon;

public abstract class JavaFunction extends Function {
	
	public JavaFunction(String funcName, String ... args) {
		super(new Identifier(funcName), args);
	}
	
	@Override
	public Expression evaluate(Context ctx) {
		Expression[] args = new Expression[getArguments().length];
		for(int i = 0; i < getArguments().length; i++) {
			args[i] = ctx.get(getArguments()[i]);
		}
		return execute(ctx, args);
	}
	
	public abstract Expression execute(Context ctx, Expression[] arguments);

}
