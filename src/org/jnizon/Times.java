package org.jnizon;

import java.util.Iterator;
import java.util.List;

public class Times extends AbstractDownCode {

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		int res = 1;
		Iterator<Expression> it = arguments.iterator();
		while(it.hasNext()) {
			Expression arg = it.next();
			if(arg instanceof IntConstant) {
				res *= ((IntConstant)arg).getValue();
				it.remove();
			}
		}
		if(arguments.size() == 0) return new IntConstant(res);
		if(res != 1) arguments.add(new IntConstant(res));
		if(arguments.size() == 1) return arguments.get(0);
		return null;
	}

}
