package org.jnizon.matching;

import org.jnizon.builtins.BooleanConstant;
import org.jnizon.builtins.Builtins;
import org.jnizon.builtins.FunctionCall;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;

public class RuleEvaluator {
	
	private PatternMatcher matcher;
	
	public RuleEvaluator() {
		matcher = new PatternMatcher();
	}

	public Expression evaluate(Context ctx, Expression root, Expression rule) {
		if (!rule.getHead().equals(Builtins.rule))
			throw new RuntimeException("WTF ?§");
		Expression pattern = rule.getChild(0);
		Expression replacement = rule.getChild(1);
		
		MatchResult result = matcher.match(ctx, pattern, root);
		if (result.isMatched()) {
			if(replacement.getHead().equals(Builtins.condition)) {
				FunctionCall cond = (FunctionCall)replacement;
				Expression res = cond.getArguments().get(0);
				Expression bool = cond.getArguments().get(1);
				Expression cres = bool.evaluate(result.getContext());
				if(cres instanceof BooleanConstant) {
					if(((BooleanConstant)cres).getValue()) return res.evaluate(result.getContext());
				}
				return root;
			}
			return replacement.evaluate(result.getContext());
		}
		return root;
	}

}
