package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;

public class Length extends AbstractDownCode {
	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression e = arguments.get(0);
		return new IntConstant(e.getChildCount());
	}
}
