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
		List<Expression> args = new ArrayList<Expression>();
		for (Expression arg : arguments) {
			Expression result = arg.evaluate(ctx);
			args.add(result);
		}
		Context funcContext = ctx.derivate();
		for (int i = 0; i < function.getArguments().length; i++) {
			Identifier argid = function.getArguments()[i];
			funcContext.put(argid, args.get(i));
		}
		
		Expression result = function.evaluate(funcContext);
		
		if (result == function) {
			return new FunctionCall(function, args);
		}
		return result;
	}

	@Override
	public int getChildCount() {
		return 1 + arguments.size();
	}

	@Override
	public Expression getChild(int index) {
		if (index == 0)
			return function;
		else if (index < arguments.size() + 1)
			return arguments.get(index - 1);
		throw new RuntimeException("Ouf of bounds");
	}

	public Function getFunction() {
		return function;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

}
