package org.jnizon;

import java.util.ArrayList;
import java.util.Iterator;
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
		for(Expression arg : arguments) {
			Expression result = arg.evaluate(ctx);
			args.add(result);
		}
		Context funcContext = ctx.derivate();
		for(int i = 0; i < function.getArguments().length; i++) {
			Identifier argid = function.getArguments()[i];
			funcContext.put(argid, args.get(i));
		}
		Expression result = function.evaluate(funcContext);
		if(result == function) return this;
		return result;
	}
	
	@Override
	public String toString() {
		String res = function.getFuncId().getName() + "[";
		Iterator<Expression> it = arguments.iterator();
		while(it.hasNext()) {
			res += it.next().toString();
			if(it.hasNext()) res += ", ";
		}
		return res + "]";
	}

}
