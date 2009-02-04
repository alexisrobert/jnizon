package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;
import org.jnizon.core.Symbol;

public class Clear extends AbstractDownCode {
	public Clear() {
	}

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		if (arguments.size() < 1)
			return null;

		for (Expression expr : arguments) {
			if (ctx.containsKey((Symbol) expr)) {
				ctx.remove((Symbol) expr);
			}
		}

		return null;
	}
}
