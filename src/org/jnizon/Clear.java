package org.jnizon;

// TODO: Migrate this to JavaFunction
public class Clear implements Expression {
	private Identifier variable;
	
	public Clear(Identifier variable) {
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
}
