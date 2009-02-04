package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;

public class SameQ extends AbstractDownCode {
	public SameQ() {
	}

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		if (arguments.size() <= 1)
			return null;

		for (int i = 1; i < arguments.size(); i++) {
			Expression a = arguments.get(i - 1).evaluate(ctx);
			Expression b = arguments.get(i).evaluate(ctx);
			if (a.equals(b) == false)
				return new BooleanConstant(false);
		}

		return new BooleanConstant(true);
	}
}
