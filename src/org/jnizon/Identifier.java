package org.jnizon;

public class Identifier implements Expression {
	
	private String name;
	
	public Identifier(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public Expression evaluate(Context ctx) {
		return ctx.get(this);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
