package org.jnizon;

import java.util.HashMap;

public class Context extends HashMap<Integer, HeapItem> {
	private static final long serialVersionUID = 4161192730488414955L;
	
	private int context_id = 0;
	
	public Context(int id) {
		this.context_id = id;
	}
	
	public boolean containsKey(Identifier id) {
		return containsKey(Integer.valueOf(id.getName().hashCode()));
	}
	
	public Expression get(Identifier id) {
		HeapItem item = get(id.getName());
		if(item == null) return id;
		return item.getValue();
	}
	
	public HeapItem get(String key) {
		return get(key.hashCode());
	}
	
	public void put(int key, HeapItem value) {
		put(Integer.valueOf(key), value);
	}
	
	public void put(String key, HeapItem value) {
		put(key.hashCode(), value);
	}
	
	public void put(HeapItem value) {
		put(value.getLabel(), value); // TODO: Change hashCode to MD4
	}
	
	public void put(Identifier id, Expression expr) {
		HeapItem item = new HeapItem(id.getName(), expr);
		put(item);
	}
	
	public void remove(Identifier id) {
		remove(Integer.valueOf(id.getName().hashCode()));
	}
	
	public int getContextId() {
		return context_id;
	}
}
