package org.jnizon;

import java.util.List;

public class Time extends AbstractDownCode {

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		return new IntConstant((int) System.currentTimeMillis());
	}

}
