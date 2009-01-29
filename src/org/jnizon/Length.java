package org.jnizon;

import java.util.List;

public class Length extends AbstractDownCode {

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression e = arguments.get(0);
		return new IntConstant(e.getChildCount());
	}

}
