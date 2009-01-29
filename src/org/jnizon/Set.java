package org.jnizon;

import java.util.ArrayList;
import java.util.List;

public class Set extends AbstractDownCode {

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression lvalue = arguments.get(0);
		Expression expr = arguments.get(1);
		SymbolValues vals;
		Symbol s;
		if(lvalue instanceof Symbol) {
			s = (Symbol)lvalue;
			vals = ctx.get(s);
		} else if (lvalue instanceof FunctionCall) {
			s = (Symbol)((FunctionCall)lvalue).getFunctionId();
			vals = ctx.get(s);	
		} else {
			 throw new RuntimeException("Cannot assign, " + lvalue + " is not a symbol");
		}
		
		List<Expression> args = new ArrayList<Expression>();
		args.add(lvalue);
		args.add(expr);
		if(lvalue instanceof Symbol) {
			vals.getOwnValues().clear();
			vals.addOwnValue(new FunctionCall(Builtins.rule, args));
		}
		else vals.addDownValue(new FunctionCall(Builtins.rule, args));
		
		ctx.put(s, vals);
		
		if(ctx.get(getSymbol()).getAttributes().contains(Builtins.holdAll)) return new NullExpression();
		return expr;
	}

}
