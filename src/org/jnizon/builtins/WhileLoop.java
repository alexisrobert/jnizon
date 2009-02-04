package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;
import org.jnizon.core.NullExpression;

public class WhileLoop extends AbstractDownCode {

	public static final int MAX_ITER = 800000;

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression condition = arguments.get(0);
		int i = 0;
		Expression loopResult = new NullExpression();
		while (true) {
			if (i >= MAX_ITER)
				throw new RuntimeException("Iteration overflow");
			Expression res = condition.evaluate(ctx);
			if (!(res instanceof BooleanConstant)
					|| (!((BooleanConstant) res).getValue()))
				return loopResult;
			if (arguments.size() > 1)
				loopResult = arguments.get(1).evaluate(ctx);
			i++;
		}
	}

}
