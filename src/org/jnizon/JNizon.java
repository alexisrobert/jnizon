package org.jnizon;

import org.antlr.runtime.RecognitionException;

public class JNizon {

	/**
	 * @param args
	 * @throws RecognitionException 
	 * @throws RecognitionException
	 */
	public static void main(String[] args) throws RecognitionException {
		Interpreter it = new Interpreter();
		// Builtins :)
		JavaFunction plus = new JavaFunction("Plus", "a", "b") {
			@Override
			public Expression execute(Expression[] arguments) {
				if(arguments[0] instanceof IntConstant && arguments[1] instanceof IntConstant) {
					int a = ((IntConstant)arguments[0]).getValue();
					int b = ((IntConstant)arguments[1]).getValue();
					return new IntConstant(a+b);
				}
				return this;
			}
		};
		it.define(plus);
		
		Shell sh = new Shell(it);
		sh.start();
	}
}
