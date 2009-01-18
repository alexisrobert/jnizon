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
			return new BooleanConstant(a.equals(b));
		}
		return this;
	}
}
