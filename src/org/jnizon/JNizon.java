package org.jnizon;

import org.antlr.runtime.RecognitionException;
import java.util.List;

public class JNizon {

	/**
	 * @param args
	 * @throws RecognitionException 
	 * @throws RecognitionException
	 */
	public static void main(String[] args) throws RecognitionException {
		// Builtins :)
		BasicForm defaultForm = new BasicForm();
		JavaFunction plus = new JavaFunction("Plus") {
			@Override
			public Expression execute(Context ctx, List<Expression> arguments) {
				if(arguments.get(0) instanceof IntConstant && arguments.get(1) instanceof IntConstant) {
					int a = ((IntConstant)arguments.get(0)).getValue();
					int b = ((IntConstant)arguments.get(1)).getValue();
					return new IntConstant(a+b);
				}
				return this;
			}
		};
		JavaFunction times = new JavaFunction("Times") {
			@Override
			public Expression execute(Context ctx, List<Expression> arguments) {
				if(arguments.get(0) instanceof IntConstant && arguments.get(1) instanceof IntConstant) {
					int a = ((IntConstant)arguments.get(0)).getValue();
					int b = ((IntConstant)arguments.get(1)).getValue();
					return new IntConstant(a*b);
				}
				return this;
			}
		};
		Function list = new JavaFunction("List") {
			@Override
			public Expression execute(Context ctx, List<Expression> arguments) {
				return new ListExpression(arguments);
			}
		};
		// end of builtins
		
		Interpreter it = new Interpreter(defaultForm);
		
		it.define(plus);
		it.addMapping(SyntaxParser.PLUS, plus.getFuncId());
		
		it.define(times);
		it.addMapping(SyntaxParser.TIMES, times.getFuncId());
		
		it.define(list);
		it.addMapping(SyntaxParser.LISTFUNC, list.getFuncId());
		
		Shell sh = new Shell(it);
		sh.start();
	}
}
