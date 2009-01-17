package org.jnizon;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;

public class JNizon {

	/**
	 * @param args
	 * @throws RecognitionException
	 */
	public static void main(String[] args) throws RecognitionException {
		ANTLRStringStream str = new ANTLRStringStream("tz = Test[1,2,3];Show[tz];");
		SyntaxLexer lexer = new SyntaxLexer(str);
		TokenStream tkstr = new CommonTokenStream(lexer);
		SyntaxParser parser = new SyntaxParser(tkstr);
		CommonTreeAdaptor adaptor = new CommonTreeAdaptor() {
			@Override
			public Object create(Token token) {
				return new SyntaxTree(token);
			}
		};
		parser.setTreeAdaptor(adaptor);
		System.out.println("Starting parsing...");
		SyntaxParser.start_return result = parser.start();
		System.out.println("Finished.");
		SyntaxTree tree = (SyntaxTree) result.getTree();
		printTree(tree, 0);
	}

	public static void printTree(CommonTree tree, int indent) {
		for (int i = 0; i < indent; i++)
			System.out.print(" - ");
		if (tree == null) {
			System.out.println("Null");
			return;
		}
		System.out.println(tree.getText());
		if (tree.getChildren() != null) {
			for (Object child : tree.getChildren()) {
				printTree((CommonTree) child, indent + 1);
			}
		}
	}

}
