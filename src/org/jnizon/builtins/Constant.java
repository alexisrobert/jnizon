package org.jnizon.builtins;

import org.jnizon.core.Expression;

public abstract class Constant implements Expression {

	@Override
	public Expression getHead() {
		return this;// TODO return type
	}

}
