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
		if(statements.size() == 0) return new NullExpression();
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
		if(index == 0) return statements.get(statements.size()-1);
		throw new RuntimeException("Ouf of bounds");
	}

	public int getChildCount() {
		return 1;
	};
}
