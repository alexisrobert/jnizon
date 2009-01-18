package org.jnizon;

import java.util.List;

public class Not extends JavaFunction {

	public Not() {
		super("Not");
	}

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		if (arguments.get(0) instanceof BooleanConstant) {
			BooleanConstant b = (BooleanConstant)arguments.get(0);
			return new BooleanConstant(!b.getValue());
		}
		return this;
	}

}
