package org.jnizon;

public abstract class Function implements Expression{

	private Identifier funcId;
	private Identifier[] arguments;

	public Function(Identifier funcId, Identifier[] arguments) {
		this.funcId = funcId;
		this.arguments = arguments;
	}
	
	public Function(Identifier funcId, String[] arguments) {
		this.funcId = funcId;
		this.arguments = new Identifier[arguments.length];
		for(int i = 0; i < arguments.length; i++) this.arguments[i] = new Identifier(arguments[i]);
	}

	public Identifier getFuncId() {
		return funcId;
	}
	
	public Identifier[] getArguments() {
		return arguments;
	}

}
