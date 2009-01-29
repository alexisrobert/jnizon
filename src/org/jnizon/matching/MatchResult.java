/**
 * 
 */
package org.jnizon.matching;

import org.jnizon.Context;
import org.jnizon.Expression;

public class MatchResult {
	private boolean matched;
	private Expression root;
	private Context ctx;
	
	private static MatchResult emptyMatch = new MatchResult(false, null, null);
	
	public static MatchResult noMatch() {
		return emptyMatch;
	}
	
	public MatchResult(boolean matched, Expression root, Context ctx) {
		this.matched = matched;
		this.root = root;
		this.ctx = ctx;
	}
	
	public Context getContext() {
		return ctx;
	}
	
	public Expression getRoot() {
		return root;
	}
	
	public boolean isMatched() {
		return matched;
	}
	
	@Override
	public String toString() {
		return "[Match:" + isMatched() + " root:" + getRoot() + " ctx: " + ctx + "]";
	}

}