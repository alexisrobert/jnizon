package org.jnizon;

public class FunctionDefinition implements Expression{
	
	private Identifier functionId;
	private Identifier[] arguments;
	private Expression functionBody;
	
	public FunctionDefinition(Identifier functionId, Identifier[] arguments, Expression functionBody) {
		this.functionId = functionId;
		this.arguments = arguments;
		this.functionBody = functionBody;
	}
	
	@Override
	public Expression evaluate(Context ctx) {
		Function func = new InlineFunction(functionId, arguments, functionBody);
		ctx.put(functionId, func);
		return new NullExpression();
	}
	
	@Override
	public int getChildCount() {
		return 1;
	}
	
	@Override
	public Expression getChild(int index) {
		//if(index == 0) return Symbol;
		throw new RuntimeException("Ouf of bounds");
	}

	@Override
	public boolean equals(Expression expr) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
