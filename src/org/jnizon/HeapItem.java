package org.jnizon;

public class HeapItem {
	private String label;
	private Expression value;
	
	public HeapItem(String label) { this(label, null); }
	public HeapItem(String label, Expression value) {
		this.label = label;
		this.value = value;
	}
	
	public String getString() {
		String formatedvalue = "null";
		if (value != null)
			formatedvalue = value.toString();
		
		return String.format("label : %s, value : %s", label, formatedvalue);
	}
	
	public String getLabel() {
		return label;
	}
	
	public Expression getValue() {
		return value;
	}
}
