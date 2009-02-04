package org.jnizon;

import java.util.List;

public class IfCondition extends AbstractDownCode {
	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression condition = arguments.get(0);
		if (!(condition instanceof BooleanConstant)) {
			if (arguments.size() > 3)
				return arguments.get(3).evaluate(ctx);
			return null;
		}
		boolean res = ((BooleanConstant) condition).getValue();
		if (res)
			return arguments.get(1).evaluate(ctx);

		if (arguments.size() > 2)
			return arguments.get(2).evaluate(ctx);
		return new NullExpression();
	}

}
