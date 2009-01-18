package org.jnizon;

public abstract class Function implements Expression {

	private Identifier funcId;

	public Function(Identifier funcId) {
		this.funcId = funcId;
	}

	public Identifier getFuncId() {
		return funcId;
	}

	@Override
	public String toString() {
		return funcId.getName();
	}

	@Override
	public int getChildCount() {
		return 1;
	}

	@Override
	public Expression getChild(int index) {
		if (index == 0)
			return funcId;
		throw new RuntimeException("Ouf of bounds");
	}

}
