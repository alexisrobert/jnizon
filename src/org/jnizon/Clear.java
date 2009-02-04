package org.jnizon;

import java.util.List;

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
