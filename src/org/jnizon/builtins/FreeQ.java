package org.jnizon.builtins;

import java.util.List;

import org.jnizon.core.AbstractDownCode;
import org.jnizon.core.Context;
import org.jnizon.core.Expression;
import org.jnizon.matching.MatchResult;
import org.jnizon.matching.PatternMatcher;

public class FreeQ extends AbstractDownCode {
	
	@Override
	public Expression execute(Context ctx, List<Expression> arguments) {
		PatternMatcher matcher = new PatternMatcher();
		MatchResult result = matcher.match(ctx, arguments.get(1), arguments.get(0));
		return new BooleanConstant(!result.isMatched());
	}
	
}
