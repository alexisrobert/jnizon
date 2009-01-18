package org.jnizon;

import java.util.Iterator;

public class BasicForm extends JavaFunction {

	public BasicForm() {
		super("BasicForm", "expr");
	}

	@Override
	public Expression execute(Context ctx, Expression[] arguments) {
		Expression expr = arguments[0];
		printForm(ctx, expr);
		return expr;
	}

	private void printForm(Context ctx, Expression expr) {
		if(expr instanceof CodeBlock) {
			CodeBlock block = (CodeBlock)expr;
			for(Expression statement : block.getStatements()) {
				printForm(ctx, statement);
				System.out.println("");
			}
		} else if(expr instanceof FunctionCall) {
			FunctionCall fc = (FunctionCall)expr;
			System.out.print(fc.getFunction().getFuncId().getName() + "[");
			Iterator<Expression> it = fc.getArguments().iterator();
			while(it.hasNext()) {
				printForm(ctx, it.next()/*.evaluate(ctx)*/);
				if(it.hasNext()) System.out.print(", ");
			}
			System.out.print("]");
		} else if(expr instanceof IntConstant) {
			IntConstant i = (IntConstant)expr;
			System.out.print(i.getValue());
		} else if(expr instanceof Identifier) {
			Identifier id = (Identifier)expr;
			System.out.print(id.evaluate(ctx));
		} else {
			System.out.print("???");
		}
	}
}
