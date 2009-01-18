package org.jnizon;

import java.util.List;

public class SameQ extends JavaFunction {
	public SameQ() {
		super("SameQ");
	}

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		if(arguments.size() >= 2) {
			Expression a = arguments.get(0).evaluate(ctx);
			Expression b = arguments.get(1).evaluate(ctx);
			return compareTrees(a,b);
		}
		return this;
	}
	
	public Expression compareTrees(Expression a, Expression b) {
		if (a.getChildCount() != b.getChildCount())
			return new IntConstant(0);
		
		if (a.getChildCount() == 0) {
			if (a.equals(b)) return new IntConstant(1);
			else return new IntConstant(0);
		} else {
			for (int i = 0; i < a.getChildCount(); i++) {
				if (((IntConstant)compareTrees(a.getChild(i), b.getChild(i))).getValue() == 0)
					return new IntConstant(1);
			}
		
			return new IntConstant(0);
		}
	}
}
