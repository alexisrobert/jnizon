package org.jnizon;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock implements Expression {

	private List<Expression> statements;

	public CodeBlock() {
		statements = new ArrayList<Expression>();
	}

	@Override
	public Expression evaluate(Context ctx) {
		Expression result = null;
		if (statements.size() == 0)
			return new NullExpression();
		for (Expression expr : statements) {
			result = expr.evaluate(ctx);
		}
		return result;
	}

	public List<Expression> getStatements() {
		return statements;
	}

	@Override
	public Expression getChild(int index) {
		if (index == 0)
			return statements.get(statements.size() - 1);
		throw new RuntimeException("Ouf of bounds");
	}

	public int getChildCount() {
		return 1;
	}

	@Override
	public boolean equals(Expression expr) {
		if (!(expr instanceof CodeBlock))
			return false;

		List<Expression> exprs = ((CodeBlock) expr).getStatements();
		if (exprs.size() != statements.size())
			return false;

		for (int i = 0; i < statements.size(); i++) {
			if (!statements.get(i).equals(exprs.get(i)))
				return false;
		}

		return true;
	}

	@Override
	public Expression getHead() {
		return statements.get(statements.size() - 1).getHead();
	}
}
