package org.jnizon;

import java.util.List;

public class Print extends AbstractDownCode {
	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		for (Expression arg : arguments) {
			System.out.print(arg);
		}
		System.out.println("");
		return new NullExpression();
	}
}
