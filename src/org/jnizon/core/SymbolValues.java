package org.jnizon.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jnizon.builtins.Builtins;
import org.jnizon.builtins.FunctionCall;

public class SymbolValues {

	private DownCode downCode;
	// private OwnCode ownCode;
	private List<Expression> ownValues;
	private List<Expression> downValues;
	private List<Symbol> attributes;

	public SymbolValues() {
		ownValues = new ArrayList<Expression>();
		downValues = new ArrayList<Expression>();
		attributes = new ArrayList<Symbol>();
	}

	public SymbolValues(Symbol s, Expression ownValue) {
		this();

		List<Expression> args = new ArrayList<Expression>();
		args.add(s);
		args.add(ownValue);

		FunctionCall rule = new FunctionCall(Builtins.rule, args);

		addOwnValue(rule);
	}

	public boolean hasDownCode() {
		return downCode != null;
	}

	public DownCode getDownCode() {
		return downCode;
	}

	public void setDownCode(DownCode downCode) {
		this.downCode = downCode;
	}

	public void addAttribute(Symbol attribute) {
		attributes.add(attribute);
	}

	public List<Symbol> getAttributes() {
		return attributes;
	}

	public List<Expression> getDownValues() {
		return downValues;
	}

	public List<Expression> getOwnValues() {
		return ownValues;
	}

	public void addOwnValue(Expression value) {
		ownValues.add(value);
	}

	public void addDownValue(Expression value) {
		downValues.add(value);
	}

	public static SymbolValues empty() {
		return new SymbolValues();
	}

	@Override
	public String toString() {
		String r = "SymbolValues[";
		r += "own:{";
		Iterator<Expression> it = ownValues.iterator();
		while (it.hasNext()) {
			r += it.next();
			if (it.hasNext())
				r += ", ";
		}
		r += "} down:{";
		it = downValues.iterator();
		while (it.hasNext()) {
			r += it.next();
			if (it.hasNext())
				r += ", ";
		}
		r += "}";
		return r + "]";
	}

}
