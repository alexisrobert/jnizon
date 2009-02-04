package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;
import org.jnizon.core.NullExpression;

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
