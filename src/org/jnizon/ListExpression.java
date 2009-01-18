package org.jnizon;

import java.util.ArrayList;
import java.util.List;

public class ListExpression implements Expression{
	
	private List<Expression> elements;
	
	public ListExpression() {
		elements = new ArrayList<Expression>();
	}
	
	public ListExpression(List<Expression> elements) {
		this.elements = elements;
	}
	
	public List<Expression> getElements() {
		return elements;
	}
	
	@Override
	public Expression evaluate(Context ctx) {
		List<Expression> evaluated = new ArrayList<Expression>();
		for(Expression element : elements) {
			evaluated.add(element.evaluate(ctx));
		}
		return new ListExpression(evaluated);
	}

	@Override
	public Expression getChild(int index) {
		return elements.get(index+1);
	}

	@Override
	public int getChildCount() {
		return elements.size() + 1;
	}
	
	
}
