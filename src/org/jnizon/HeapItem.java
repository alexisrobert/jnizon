package org.jnizon;

public class HeapItem {
	private String label;
	private SymbolValues value;
	private boolean marked = false;
	
	public HeapItem(String label) { this(label, null); }
	public HeapItem(String label, SymbolValues value) {
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
	
	public SymbolValues getValue() {
		return value;
	}
	
	public void setMarked(boolean mark) {
		marked = mark;
	}
	
	public boolean isMarked() {
		return marked;
	}
}
