package org.jnizon;

public class Function implements Expression{

	private Identifier funcId;
	private CodeBlock code;
	private Identifier[] arguments;

	public Function(Identifier funcId, CodeBlock code, Identifier[] arguments) {
		this.funcId = funcId;
		this.code = code;
		this.arguments = arguments;
	}

	public CodeBlock getCode() {
		return code;
	}

	public Identifier getFuncId() {
		return funcId;
	}
	
	public Identifier[] getArguments() {
		return arguments;
	}
	
	@Override
	public Expression evaluate(Context ctx) {
		return this;
	}

}
