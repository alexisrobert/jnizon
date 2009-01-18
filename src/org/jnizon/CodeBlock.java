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
		for(Expression expr : statements) {
			result = expr.evaluate(ctx);
		}
		return result;
	}
	
	public List<Expression> getStatements() {
		return statements;
	}
	
}
