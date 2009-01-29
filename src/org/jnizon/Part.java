package org.jnizon;

import java.util.List;

public class Part extends AbstractDownCode {

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression e = arguments.get(0);
		Expression part = arguments.get(1);
		if(!(part instanceof IntConstant)) return null;
		int idx = ((IntConstant)part).getValue();
		if(idx == 0) return e.getHead();
		return e.getChild(idx - 1);
	}

}
