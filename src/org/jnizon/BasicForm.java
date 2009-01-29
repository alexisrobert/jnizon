package org.jnizon;

import java.util.Iterator;
import java.util.List;

public class BasicForm extends AbstractDownCode {

	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		Expression expr = arguments.get(0);
		printForm(ctx, expr);
		return expr;
	}

	private void printForm(Context ctx, Expression expr) {
		if (expr instanceof CodeBlock) {
			CodeBlock block = (CodeBlock) expr;
			for (Expression statement : block.getStatements()) {
				printForm(ctx, statement);
				System.out.println("");
			}
		} else if (expr instanceof FunctionCall) {
			FunctionCall fc = (FunctionCall) expr;
			if (fc.getFunctionId().equals(Builtins.plus)) {
				printForm(ctx, fc.getArguments().get(0));
				for (int i = 1; i < fc.getArguments().size(); i++) {
					System.out.print(" + ");
					printForm(ctx, fc.getArguments().get(i));
				}
			} else if (fc.getFunctionId().equals(Builtins.times)) {
				printForm(ctx, fc.getArguments().get(0));
				for (int i = 1; i < fc.getArguments().size(); i++) {
					System.out.print("*");
					printForm(ctx, fc.getArguments().get(i));
				}
			} else if (fc.getFunctionId().equals(Builtins.pattern)) {
				Expression e = fc.getArguments().get(0);
				if (e instanceof Symbol) {
					Symbol symbol = (Symbol) e;
					System.out.print(symbol.getName());
					Expression z = fc.getArguments().get(1);
					if (z instanceof FunctionCall
							&& ((FunctionCall) z).getFunctionId().equals(
									Builtins.blank)) {
						printForm(ctx, z);
					} else {
						System.out.print(":");
						printForm(ctx, z);
					}
				} else
					System.out.print("[Not now]");// TODO do it
			} else if (fc.getFunctionId().equals(Builtins.blank)) {
				System.out.print("_");
			} else {
				printForm(ctx, fc.getFunctionId());
				System.out.print("[");
				Iterator<Expression> it = fc.getArguments().iterator();
				while (it.hasNext()) {
					printForm(ctx, it.next()/* .evaluate(ctx) */);
					if (it.hasNext())
						System.out.print(", ");
				}
				System.out.print("]");
			}
		} else if (expr instanceof IntConstant) {
			IntConstant i = (IntConstant) expr;
			System.out.print(i.toString());
		} else if (expr instanceof Symbol) {
			Symbol id = (Symbol) expr;
			System.out.print(id.evaluate(ctx));
		} else if (expr instanceof BooleanConstant) {
			System.out.print(((BooleanConstant) expr).toString());
		} /*
		 * else if(expr instanceof ListExpression) { ListExpression le =
		 * (ListExpression)expr; Iterator<Expression> it =
		 * le.getElements().iterator(); System.out.print("{");
		 * while(it.hasNext()) { printForm(ctx, it.next()); if(it.hasNext())
		 * System.out.print(", "); } System.out.print("}"); }
		 */else if (expr instanceof NullExpression) {

		} else {
			System.out.print("<???>");
		}
	}

}
