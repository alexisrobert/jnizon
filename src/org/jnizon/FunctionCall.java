package org.jnizon;

import java.util.ArrayList;
import java.util.List;

public class FunctionCall implements Expression {
	
	private Function function;
	private List<Expression> arguments;
	
	public FunctionCall(Function function, List<Expression> arguments) {
		this.function = function;
		this.arguments = arguments;
	}
	
	@Override
	public Expression evaluate(Context ctx) {
		List<Constant> args = new ArrayList<Constant>();
		for(Expression arg : arguments) {
			Expression result = arg.evaluate(ctx);
			if(result instanceof Constant) args.add((Constant)result);
			else return this;//Some arguments are not fully evaluated
		}
		Context funcContext = ctx.derivate();
		for(int i = 0; i < function.getArguments().length; i++) {
			Identifier argid = function.getArguments()[i];
			funcContext.put(argid, args.get(i));
		}
		return function.getCode().evaluate(funcContext);
	}

}
