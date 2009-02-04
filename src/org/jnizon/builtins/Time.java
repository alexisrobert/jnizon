package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;

public class Time extends AbstractDownCode {

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		return new IntConstant((int) System.currentTimeMillis());
	}

}
