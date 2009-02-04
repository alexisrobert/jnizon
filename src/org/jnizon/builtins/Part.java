package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;

public class Part extends AbstractDownCode {

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression e = arguments.get(0);
		Expression part = arguments.get(1);
		if (!(part instanceof IntConstant))
			return null;
		int idx = ((IntConstant) part).getValue();
		if (idx == 0)
			return e.getHead();
		return e.getChild(idx - 1);
	}

}
