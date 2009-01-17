package org.jnizon;

public class HeapItem {
	private String label;
	private Object value;
	
	public HeapItem(String label) { this(label, null); }
	public HeapItem(String label, Object value) {
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
	
	public Object getValue() {
		return value;
	}
}
