package org.jnizon;

import java.io.IOException;

import org.antlr.runtime.RecognitionException;
import org.jnizon.builtins.BasicForm;
import org.jnizon.builtins.Builtins;
import org.jnizon.builtins.Clear;
import org.jnizon.builtins.FreeQ;
import org.jnizon.builtins.Greater;
import org.jnizon.builtins.IfCondition;
import org.jnizon.builtins.Length;
import org.jnizon.builtins.Less;
import org.jnizon.builtins.Not;
import org.jnizon.builtins.Part;
import org.jnizon.builtins.Plus;
import org.jnizon.builtins.Print;
import org.jnizon.builtins.SameQ;
import org.jnizon.builtins.Set;
import org.jnizon.builtins.Time;
import org.jnizon.builtins.Times;
import org.jnizon.builtins.WhileLoop;
import org.jnizon.parsing.SyntaxParser;

public class JNizon {

	public static final String[] initFiles = { "init.m" };

	public static void main(String[] args) throws RecognitionException,
			IOException {
		// Builtins :)

		/*
		 * Function sameq = new SameQ(); Function not = new Not();
		 */
		// end of builtins
		Interpreter it = new Interpreter(Builtins.basicForm);

		it.define(Builtins.basicForm, new BasicForm());
		it.define(Builtins.plus, new Plus(), Builtins.flat);
		it.define(Builtins.times, new Times(), Builtins.flat);
		it.define(Builtins.blank);
		it.define(Builtins.pattern);
		it.define(Builtins.condition);
		it.define(Builtins.holdFirst);
		it.define(Builtins.holdAll);
		it.define(Builtins.holdRest);
		it.define(Builtins.freeq, new FreeQ());
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
		it.define(Builtins.clear, new Clear(), Builtins.holdAll);

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

		for (String filename : initFiles) {
			new CodeFile(filename).evaluate(it);
		}

		Shell sh = new Shell(it);
		sh.start();
	}
}
