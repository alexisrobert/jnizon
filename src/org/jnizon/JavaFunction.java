package org.jnizon;

import java.util.List;

public abstract class JavaFunction extends Function {
	
	public JavaFunction(String funcName) {
		super(new Identifier(funcName));
	}
	
	@Override
	public Expression evaluate(Context ctx) {
		return this;
	}
	
	public abstract Expression execute(Context ctx, List<Expression> arguments);

}
