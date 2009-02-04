package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;

public class Not extends AbstractDownCode {

	public Not() {
	}

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		if (arguments.get(0) instanceof BooleanConstant) {
			BooleanConstant b = (BooleanConstant) arguments.get(0);
			return new BooleanConstant(!b.getValue());
		}
		return null;
	}

}
