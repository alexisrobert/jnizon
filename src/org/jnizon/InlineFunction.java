package org.jnizon;

public class InlineFunction extends Function {

	private Expression code;

	public InlineFunction(Identifier functionId, Identifier[] args,
			Expression code) {
		super(functionId, args);
		this.code = code;
	}

	@Override
	public Expression evaluate(Context ctx) {
		return code.evaluate(ctx);
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
