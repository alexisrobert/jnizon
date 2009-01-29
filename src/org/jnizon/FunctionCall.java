package org.jnizon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.jnizon.matching.MatchResult;
import org.jnizon.matching.PatternMatcher;

public class FunctionCall implements Expression {

	private Expression functionId;
	private List<Expression> arguments;

	public FunctionCall(Expression functionId, List<Expression> arguments) {
		this.functionId = functionId;
		this.arguments = arguments;
	}

	@Override
	public Expression evaluate(Context ctx) {
		Expression fid = functionId.evaluate(ctx);
		SymbolValues sValues = null;

		List<Symbol> attributes = Collections.emptyList();

		if (fid instanceof Symbol) {
			sValues = ctx.get((Symbol) fid);
			attributes = sValues.getAttributes();
		}
		
		if(attributes.contains(Builtins.flat)) {
			List<Expression> nArgs = new ArrayList<Expression>(arguments);
			ListIterator<Expression> it = nArgs.listIterator();
			boolean changed = false;
			while(it.hasNext()) {
				Expression arg = it.next();
				if(arg instanceof FunctionCall) {
					FunctionCall call = (FunctionCall)arg;
					if(call.getFunctionId().equals(functionId)) {
						changed = true;
						it.remove();
						for(Expression na : call.getArguments())
							it.add(na);
					}
				}
			}
			if(changed) return new FunctionCall(functionId, nArgs);
		}

		List<Expression> evaluatedArguments;
		if (!attributes.contains(Builtins.holdAll)) {
			evaluatedArguments = new ArrayList<Expression>();
			if (attributes.contains(Builtins.holdRest)) {
				evaluatedArguments.add(arguments.get(0).evaluate(ctx));
				evaluatedArguments.addAll(arguments.subList(1, arguments.size())); 
			} else {
				int start = 0;
				if (attributes.contains(Builtins.holdFirst)) {
					start = 1;
					evaluatedArguments.add(arguments.get(0));
				}
				for (int i = start; i < arguments.size(); i++)
					evaluatedArguments.add(arguments.get(i).evaluate(ctx));
			}
		} else {
			evaluatedArguments = arguments;
		}

		if (sValues == null)
			return new FunctionCall(fid, evaluatedArguments);
		if (sValues.hasDownCode()) {
			Expression r = sValues.getDownCode().execute(ctx,
					evaluatedArguments);
			if (r == null)
				return new FunctionCall(fid, evaluatedArguments);
			return r;
		}

		List<Expression> downValues = sValues.getDownValues();
		PatternMatcher matcher = new PatternMatcher();
		for (Expression rule : downValues) {
			if (!rule.getHead().equals(Builtins.rule))
				throw new RuntimeException("No rule in downvalues");
			Expression pattern = rule.getChild(0);
			Expression replacement = rule.getChild(1);
			MatchResult result = matcher.match(ctx, pattern, this);
			if (result.isMatched()) {
				if (result.getRoot() != this)
					throw new RuntimeException("WTF ?");
				return replacement.evaluate(result.getContext());
			}
		}
		return new FunctionCall(fid, evaluatedArguments);
	}

	@Override
	public int getChildCount() {
		return arguments.size();
	}

	@Override
	public Expression getChild(int index) {
		return arguments.get(index);
	}

	public Expression getFunctionId() {
		return functionId;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	@Override
	public Expression getHead() {
		return functionId;
	}

	@Override
	public String toString() {
		String str = functionId + "[";
		Iterator<Expression> it = arguments.iterator();
		while (it.hasNext()) {
			str += it.next();
			if (it.hasNext())
				str += ", ";
		}
		return str + "]";
	}

	@Override
	public boolean equals(Expression expr) {
		if (expr instanceof FunctionCall) {
			FunctionCall other = (FunctionCall) expr;
			if (other.getChildCount() != 0 || getChildCount() != 0)
				throw new RuntimeException("Not done yet");
			return getHead().equals(other.getHead());
		}
		throw new RuntimeException("Not done yet");
	}

}
