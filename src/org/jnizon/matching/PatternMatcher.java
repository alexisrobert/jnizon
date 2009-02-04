package org.jnizon.matching;

import org.jnizon.BooleanConstant;
import org.jnizon.Builtins;
import org.jnizon.Context;
import org.jnizon.Expression;
import org.jnizon.Symbol;
import org.jnizon.SymbolValues;

public class PatternMatcher {

	public MatchResult match(Context rootCtx, Expression pattern,
			Expression expr) {
		return findMatch(rootCtx, pattern, expr);
	}

	private MatchResult findMatch(Context rootCtx, Expression pattern,
			Expression root) {
		Context ctx = rootCtx.derivate();
		if (!matchTree(ctx, pattern, root)) {
			for (int i = 0; i < root.getChildCount(); i++) {
				MatchResult result = findMatch(rootCtx, pattern, root
						.getChild(i));
				if (result.isMatched())
					return result;

			}
			return MatchResult.noMatch();
		}
		return new MatchResult(true, root, ctx);
	}

	private boolean matchTree(Context ctx, Expression pattern, Expression tree) {
		if (pattern.getHead().equals(Builtins.condition)) {
			if (pattern.getChildCount() < 2)
				return false;

			if (matchTree(ctx, pattern.getChild(0), tree)) {
				Expression condition = pattern.getChild(1).evaluate(ctx);
				if (condition instanceof BooleanConstant
						&& ((BooleanConstant) condition).getValue() == true)
					return true;
			}
			return false;
		}
		if (pattern.getHead().equals(Builtins.pattern)) {
			if (matchTree(ctx, pattern.getChild(1), tree)) {
				Symbol named = (Symbol) pattern.getChild(0);
				ctx.putLocal(named, new SymbolValues(named, tree));
				return true;
			}
			return false;
		}
		if (pattern.getHead().equals(Builtins.blank)) {
			return true;
		}
		if (!pattern.getHead().equals(tree.getHead()))
			return false;
		if (tree.getChildCount() == pattern.getChildCount()) {
			int cc = tree.getChildCount();
			if (cc == 0)
				return pattern.equals(tree);

			for (int i = 0; i < cc; i++) {
				if (!matchTree(ctx, pattern.getChild(i), tree.getChild(i)))
					return false;
			}
			return true;

		}
		return false;
	}

}
