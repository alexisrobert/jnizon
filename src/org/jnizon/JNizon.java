package org.jnizon;

import org.antlr.runtime.RecognitionException;

public class JNizon {

	/**
	 * @param args
	 * @throws RecognitionException 
	 * @throws RecognitionException
	 */
	public static void main(String[] args) throws RecognitionException {
		Interpreter it = new Interpreter(Builtins.basicForm);
		
		it.define(Builtins.basicForm, new BasicForm());
		it.define(Builtins.plus, new Plus(), Builtins.flat);
		it.define(Builtins.times, new Times(), Builtins.flat);
		it.define(Builtins.blank);
		it.define(Builtins.pattern);
		it.define(Builtins.holdFirst);
		it.define(Builtins.holdAll);
		it.define(Builtins.holdRest);
		it.define(Builtins.set, new Set(), Builtins.holdFirst);
		it.define(Builtins.setDelayed, new Set(), Builtins.holdAll);
		it.define(Builtins.time, new Time());
		it.define(Builtins.ifCondition, new IfCondition(), Builtins.holdRest);
		it.define(Builtins.greater, new Greater());
		it.define(Builtins.less, new Less());
		it.define(Builtins.whileLoop, new WhileLoop(), Builtins.holdAll);
		it.define(Builtins.print, new Print());
		it.define(Builtins.part, new Part());
		it.define(Builtins.sameq, new SameQ());
		it.define(Builtins.not, new Not());
		it.define(Builtins.length, new Length());
		
		it.addMapping(SyntaxParser.PLUS, Builtins.plus);
		
		it.addMapping(SyntaxParser.TIMES, Builtins.times);
		
		it.addMapping(SyntaxParser.LIST, Builtins.list);
		
		it.addMapping(SyntaxParser.PART, Builtins.part);
		
		it.addMapping(SyntaxParser.SAMEQ, Builtins.sameq);
		
		it.addMapping(SyntaxParser.NOT, Builtins.not);
		
		it.addMapping(SyntaxParser.GREATER, Builtins.greater);
		it.addMapping(SyntaxParser.LESS, Builtins.less);
		
		it.addMapping(SyntaxParser.BLANK, Builtins.blank);
		
		it.addMapping(SyntaxParser.PATTERN, Builtins.pattern);
		
		it.addMapping(SyntaxParser.SET, Builtins.set);
		
		it.addMapping(SyntaxParser.SETDELAYED, Builtins.setDelayed);
		
		it.define(Builtins.rule);
		
		// <YOUHOU>
		it.evaluate("Abs[x_] := If[x > 0, x, -x]");
		// </YOUHOU>
		
		Shell sh = new Shell(it);
		sh.start();
	}
}
