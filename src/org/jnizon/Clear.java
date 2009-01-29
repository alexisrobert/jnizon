package org.jnizon;

// TODO: Migrate this to JavaFunction
public class Clear implements Expression {
	private Symbol variable;
	
	public Clear(Symbol variable) {
		this.variable = variable;
	}
	
	@Override
	public Expression evaluate(Context ctx) {
		if (ctx.containsKey(variable))
			ctx.remove(variable);
		
		return null;
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
	
	@Override
	public Expression getHead() {
		// TODO Auto-generated method stub
		return null;
	}
}
