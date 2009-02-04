package org.jnizon;

public abstract class AbstractDownCode implements DownCode {

	private Symbol symbol;

	@Override
	public Symbol getSymbol() {
		return symbol;
	}

	@Override
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

}
