package org.jnizon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.jnizon.builtins.BooleanConstant;
import org.jnizon.builtins.Clear;
import org.jnizon.builtins.CodeBlock;
import org.jnizon.builtins.FunctionCall;
import org.jnizon.builtins.IntConstant;
import org.jnizon.core.Context;
import org.jnizon.core.DownCode;
import org.jnizon.core.Expression;
import org.jnizon.core.Heap;
import org.jnizon.core.NullExpression;
import org.jnizon.core.Symbol;
import org.jnizon.core.SymbolValues;
import org.jnizon.parsing.SyntaxTree;

public class Interpreter {
	private CommonTreeAdaptor adaptor;
	private Symbol defaultForm;
	private Map<Integer, Symbol> mappings;
	private static Heap heap;
	private static Context global_context;

	public Interpreter(Symbol defaultForm) {
		adaptor = new CommonTreeAdaptor() {
			@Override
			public Object create(Token token) {
				return new SyntaxTree(token);
			}
		};
		this.defaultForm = defaultForm;
		mappings = new HashMap<Integer, Symbol>();

		heap = new Heap();
		global_context = heap.getContext(0);

		define(defaultForm);
	}

	public static Heap getHeap() {
		return heap;
	}

	public void addMapping(int token, Symbol function) {
		mappings.put(token, function);
	}

	public void define(Symbol s) {
		global_context.put(s, SymbolValues.empty());
	}

	public void define(Symbol s, DownCode code, Symbol... attributes) {
		define(s);
		code.setSymbol(s);
		global_context.get(s).setDownCode(code);
		for (Symbol attribute : attributes)
			global_context.get(s).addAttribute(attribute);
	}

	public Expression evaluate(String code) throws RecognitionException {
		Expression main = parse(code);
		return new FunctionCall(defaultForm, Collections.singletonList(main
				.evaluate(global_context))).evaluate(global_context);
		// return main.evaluate(global_context);
	}

	public Expression parse(String code) throws RecognitionException {
		ANTLRStringStream str = new ANTLRStringStream(code);
		SyntaxLexer lexer = new SyntaxLexer(str);
		TokenStream tkstr = new CommonTokenStream(lexer);
		SyntaxParser parser = new SyntaxParser(tkstr);

		parser.setTreeAdaptor(adaptor);

		SyntaxParser.start_return result = parser.start();

		SyntaxTree tree = (SyntaxTree) result.getTree();
		// printTree(tree, 0);// print raw AST

		return convertTree(tree);
	}

	public Expression convertTree(CommonTree tree) {
		if (tree == null)
			return new NullExpression();
		if (tree.getType() == SyntaxParser.MINUSONE) {
			return new IntConstant(-1);
		}
		if (tree.getType() == SyntaxParser.CODEBLOCK) {
			if (tree.getChildren() != null) {
				if (tree.getChildCount() == 1)
					return convertTree((CommonTree) tree.getChild(0));// Simplify
				// a bit
				// the
				// tree,
				// no 1
				// element
				// codeblock
				CodeBlock block = new CodeBlock();
				for (Object child : tree.getChildren()) {
					block.getStatements().add(convertTree((CommonTree) child));
				}
				return block;
			}
			return new NullExpression();
		} else if (tree.getType() == SyntaxParser.CLEAR) {
			Symbol lval = id((CommonTree) tree.getChild(0));
			List<Expression> values = new ArrayList<Expression>();
			values.add(lval);

			new Clear().execute(global_context, values);

			return lval;
		} else if (tree.getType() == SyntaxParser.INT) {
			return new IntConstant(Integer.parseInt(tree.getText()));
		} else if (tree.getType() == SyntaxParser.BOOL) {
			return BooleanConstant.parseBool(tree.getText());
		} else if (tree.getType() == SyntaxParser.ID
				&& tree.getChildCount() == 0) {
			return id(tree);
		} else if (tree.getType() == SyntaxParser.FUNCTIONCALL) {
			Symbol funcid;
			funcid = mappings.get(tree.getChild(0).getType());
			if (funcid == null)
				funcid = id((CommonTree) tree.getChild(0));
			List<Expression> arguments = new ArrayList<Expression>();
			for (int i = 0; i < tree.getChildCount() - 1; i++) {
				arguments.add(convertTree((CommonTree) tree.getChild(i + 1)));
			}
			return new FunctionCall(funcid, arguments);
		} else
			throw new RuntimeException("Failed : " + tree);
	}

	public static Symbol id(CommonTree tree) {
		return new Symbol(tree.getText());
	}

	public static void printTree(CommonTree tree, int indent) {
		for (int i = 0; i < indent; i++)
			System.out.print(" - ");
		if (tree == null) {
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
