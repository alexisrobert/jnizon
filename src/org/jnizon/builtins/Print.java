package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;
import org.jnizon.core.NullExpression;

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
