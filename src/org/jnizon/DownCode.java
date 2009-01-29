package org.jnizon;

import java.util.List;

public interface DownCode {
	
	public void setSymbol(Symbol symbol);
	public Symbol getSymbol();
	public Expression execute(Context ctx, List<Expression> arguments);
}
