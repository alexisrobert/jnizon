package org.jnizon;

import java.util.Iterator;
import java.util.List;

public class BasicForm extends JavaFunction {

	public BasicForm() {
		super("BasicForm");
	}

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression expr = arguments.get(0);
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
			if(fc.getFunctionId().getName().equals("Plus")) {
				printForm(ctx, fc.getArguments().get(0));
				System.out.print(" + ");
				printForm(ctx, fc.getArguments().get(1));
			} else if(fc.getFunctionId().getName().equals("Times")) {
				printForm(ctx, fc.getArguments().get(0));
				System.out.print("*");
				printForm(ctx, fc.getArguments().get(1));
			} else {
				System.out.print(fc.getFunctionId().getName() + "[");
				Iterator<Expression> it = fc.getArguments().iterator();
				while(it.hasNext()) {
					printForm(ctx, it.next()/*.evaluate(ctx)*/);
					if(it.hasNext()) System.out.print(", ");
				}
				System.out.print("]");
			}
		} else if(expr instanceof IntConstant) {
			IntConstant i = (IntConstant)expr;
			System.out.print(i.toString());
		} else if(expr instanceof Identifier) {
			Identifier id = (Identifier)expr;
			System.out.print(id.evaluate(ctx));
		} else if(expr instanceof BooleanConstant) {
			System.out.print(((BooleanConstant)expr).toString());
		} else if(expr instanceof ListExpression) {
			ListExpression le = (ListExpression)expr;
			Iterator<Expression> it = le.getElements().iterator();
			System.out.print("{");
			while(it.hasNext()) {
				printForm(ctx, it.next()/*.evaluate(ctx)*/);
				if(it.hasNext()) System.out.print(", ");
			}
			System.out.print("}");
		} else if(expr instanceof NullExpression) {
			
		} else {
			System.out.print("<???>");
		}
	}

	@Override
	public boolean equals(Expression expr) {
		if (expr instanceof BasicForm) return true;
		else return false;
	}
}
