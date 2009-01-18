package org.jnizon;

public class Function {

	private Identifier funcId;
	private CodeBlock code;
	private Identifier[] arguments;

	public Function(Identifier funcId, CodeBlock code, Identifier[] arguments) {
		this.funcId = funcId;
		this.code = code;
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

}
