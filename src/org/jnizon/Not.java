package org.jnizon;

import java.util.List;

public class Not extends AbstractDownCode {

	public Not() {
	}

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		if (arguments.get(0) instanceof BooleanConstant) {
			BooleanConstant b = (BooleanConstant)arguments.get(0);
			return new BooleanConstant(!b.getValue());
		}
		return null;
	}

}
