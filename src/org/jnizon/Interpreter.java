package org.jnizon;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;

public class Interpreter {
	private CommonTreeAdaptor adaptor;
	
	public Interpreter() {
		adaptor = new CommonTreeAdaptor() {
			@Override
			public Object create(Token token) {
				return new SyntaxTree(token);
			}
		};
	}
	
	public void evaluate(String code) throws RecognitionException {
		ANTLRStringStream str = new ANTLRStringStream(code);
		SyntaxLexer lexer = new SyntaxLexer(str);
		TokenStream tkstr = new CommonTokenStream(lexer);
		SyntaxParser parser = new SyntaxParser(tkstr);

		parser.setTreeAdaptor(adaptor);
		
		System.out.println("Starting parsing...");
		SyntaxParser.start_return result = parser.start();
		System.out.println("Finished.");
		
		SyntaxTree tree = (SyntaxTree) result.getTree();
		printTree(tree, 0);
		
		Heap heap = new Heap();
		Context ctx = heap.getContext(0);
		CodeBlock main = (CodeBlock)convertTree(tree);
		Expression expr = main.evaluate(ctx);
		System.out.println("Result : " + expr);
	
	
	public static Expression convertTree(CommonTree tree) {
		if(tree.getType() == SyntaxParser.ROOT) {
			CodeBlock block = new CodeBlock();
			if (tree.getChildren() != null) {
				for (Object child : tree.getChildren()) {
					block.getStatements().add(convertTree((CommonTree)child));
				}
			}
			return block;
		} else if(tree.getType() == SyntaxParser.ASSIGNEMENT) {
			Identifier lval = id((CommonTree)tree.getChild(0));
			Expression rVal = convertTree((CommonTree)tree.getChild(1));
			return new Assignment((Identifier)lval, rVal);
		} else if(tree.getType() == SyntaxParser.INT) {
			return new IntConstant(Integer.parseInt(tree.getText()));
		}else if(tree.getType() == SyntaxParser.ID) {
			return id(tree);
		} else if(tree.getType() == SyntaxParser.FUNCTIONCALL) {
			Identifier funcid = id((CommonTree)tree.getChild(0));
			List<Expression> arguments = new ArrayList<Expression>();
			for(int i = 1; i < tree.getChildCount(); i++) {
				arguments.add(convertTree((CommonTree)tree.getChild(i)));
			}
			Expression func = ctx.get(funcid);
			if(func instanceof Function)
				return new FunctionCall((Function)func, arguments);
			else throw new RuntimeException("Id : " + funcid + " is not a function.");
		}
		return null;
	}
	
	public static Identifier id(CommonTree tree) {
		return new Identifier(tree.getText());
	}

	public static void printTree(CommonTree tree, int indent) {
		for (int i = 0; i < indent; i++)
			System.out.print(" - ");
		if (tree == null) {
			System.out.println("Null");
			return;
		}
		System.out.println(tree.getText() + " : " + tree.getToken().getType());
		if (tree.getChildren() != null) {
			for (Object child : tree.getChildren()) {
				printTree((CommonTree) child, indent + 1);
			}
		}
	}
}