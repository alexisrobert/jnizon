package org.jnizon;

import org.antlr.runtime.RecognitionException;

public class JNizon {

	/**
	 * @param args
	 * @throws RecognitionException 
	 * @throws RecognitionException
	 */
	public static void main(String[] args) throws RecognitionException {
		// Builtins :)
		
		
		
		
		/*Function sameq = new SameQ();
		Function not = new Not();*/
		// end of builtins
		
		Interpreter it = new Interpreter(Builtins.basicForm);
		
		it.define(Builtins.basicForm, new BasicForm());
		it.define(Builtins.plus, new Plus());
		it.define(Builtins.times, new Times());
		it.define(Builtins.blank);
		it.define(Builtins.pattern);
		it.define(Builtins.holdFirst);
		it.define(Builtins.set, new Set(), Builtins.holdFirst);
		it.define(Builtins.setDelayed, new Set(), Builtins.holdAll);
		it.define(Builtins.time, new Time());
		
		it.addMapping(SyntaxParser.PLUS, Builtins.plus);
		
		it.addMapping(SyntaxParser.TIMES, Builtins.times);
		
		//it.addMapping(SyntaxParser.LISTFUNC, it.define(Builtins.list));
		
		/*it.define(sameq);
		it.addMapping(SyntaxParser.SAMEQ, sameq.getFuncId());
		
		it.define(not);
		it.addMapping(SyntaxParser.NOT, not.getFuncId());*/
		
		
		it.addMapping(SyntaxParser.BLANK, Builtins.blank);
		
		it.addMapping(SyntaxParser.PATTERN, Builtins.pattern);
		
		it.addMapping(SyntaxParser.SET, Builtins.set);
		
		it.addMapping(SyntaxParser.SETDELAYED, Builtins.setDelayed);
		
		it.define(Builtins.rule);
		
		Shell sh = new Shell(it);
		sh.start();
	}
}
