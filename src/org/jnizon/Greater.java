package org.jnizon;

import java.util.List;

public class Greater extends AbstractDownCode {

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression e1 = arguments.get(0);
		Expression e2 = arguments.get(1);

		if (e1 instanceof IntConstant && e2 instanceof IntConstant) {
			return new BooleanConstant(
					((IntConstant) e1).getValue() > ((IntConstant) e2)
							.getValue());
		}
		return null;
	}

}
