package org.jnizon;

import org.antlr.runtime.RecognitionException;

public class JNizon {

	/**
	 * @param args
	 * @throws RecognitionException 
	 * @throws RecognitionException
	 */
	public static void main(String[] args) throws RecognitionException {
		// Builtins :)
		BasicForm defaultForm = new BasicForm();
		JavaFunction plus = new JavaFunction("Plus", "a", "b") {
			@Override
			public Expression execute(Context ctx, Expression[] arguments) {
				for(int i = 0; i < arguments.length; i++) {
					System.out.println("A" + i + " : " + arguments[i]);
				}
				if(arguments[0] instanceof IntConstant && arguments[1] instanceof IntConstant) {
					int a = ((IntConstant)arguments[0]).getValue();
					int b = ((IntConstant)arguments[1]).getValue();
					return new IntConstant(a+b);
				}
				return this;
			}
		};
		JavaFunction times = new JavaFunction("Times", "a", "b") {
			@Override
			public Expression execute(Context ctx, Expression[] arguments) {
				if(arguments[0] instanceof IntConstant && arguments[1] instanceof IntConstant) {
					int a = ((IntConstant)arguments[0]).getValue();
					int b = ((IntConstant)arguments[1]).getValue();
					return new IntConstant(a*b);
				}
				return this;
			}
		};
		// end of builtins
		
		Interpreter it = new Interpreter(defaultForm);
		
		it.define(plus);
		it.addMapping(SyntaxParser.PLUS, plus.getFuncId());
		
		it.define(times);
		it.addMapping(SyntaxParser.TIMES, times.getFuncId());
		
		Shell sh = new Shell(it);
		sh.start();
	}
}
