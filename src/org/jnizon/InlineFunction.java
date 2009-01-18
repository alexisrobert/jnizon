package org.jnizon;

public class InlineFunction extends Function {

	private Expression code;
	private Identifier[] arguments;

	public InlineFunction(Identifier functionId, Identifier[] arguments,
			Expression code) {
		super(functionId);
		this.arguments = arguments;
		this.code = code;
	}
	
	public InlineFunction(Identifier funcId, String[] arguments) {
		super(funcId);
		this.arguments = new Identifier[arguments.length];
		for(int i = 0; i < arguments.length; i++) this.arguments[i] = new Identifier(arguments[i]);
	}

	@Override
	public Expression evaluate(Context ctx) {
		return code.evaluate(ctx);
	}
	
	public Identifier[] getArguments() {
		return arguments;
	}

	@Override
	public Expression getChild(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getChildCount() {
		return 0;
	}

}
