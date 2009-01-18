package org.jnizon;

import java.util.List;

public class SameQ extends JavaFunction {
	public SameQ() {
		super("SameQ");
	}

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		if(arguments.size() <= 1) return this;
		
		for (int i = 1; i < arguments.size(); i++) {
			Expression a = arguments.get(i-1).evaluate(ctx);
			Expression b = arguments.get(i).evaluate(ctx);
			if (a.equals(b) == false)
				return new BooleanConstant(false);
		}
		
		return new BooleanConstant(true);
	}
}
