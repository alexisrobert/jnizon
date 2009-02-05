package org.jnizon.core;

import java.util.List;

import org.jnizon.matching.RuleEvaluator;

public class Symbol implements Expression {

	private String name;

	public Symbol(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public Expression evaluate(Context ctx) {
		List<Expression> values = ctx.get(this).getOwnValues();
		RuleEvaluator ruleEvaluator = new RuleEvaluator();
		for (Expression rule : values) {
			Expression result = ruleEvaluator.evaluate(ctx, this, rule);
			if (result.equals(this))
				return this;// TODO quite nasty bug fix, no time to investigate
							// but it is not the proper way (try to remove this
							// and do : f[x_] := x+1 then f[x])
			if(result != this) return result;
		}
		return this;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public Expression getChild(int index) {
		throw new RuntimeException("SQDOinsqd");
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Expression expr) {
		if (!(expr instanceof Symbol))
			return false;
		return ((Symbol) expr).getName().equals(this.name);
	}

	@Override
	public Expression getHead() {
		return this;// TODO return Builtins.Symbol
	}

}
