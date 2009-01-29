package org.jnizon;

public abstract class Function implements Expression {

	private Symbol funcId;

	public Function(Symbol funcId) {
		this.funcId = funcId;
	}

	public Symbol getFuncId() {
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
	
	@Override
	public Expression getHead() {
		throw new RuntimeException("Dont do that !");
	}

	public boolean equals(Expression expr) {
		if (!(expr instanceof Function)) return false;
		return ((Function)expr).getFuncId().equals(funcId);
	}
}
