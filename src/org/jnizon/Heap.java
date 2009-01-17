package org.jnizon;

/*
 * Example :
 * 
 * 		Heap h = new Heap();
 *		System.out.println("Putting a string named 'test' in default context ...");
 *		h.getContext(0).put(new HeapItem("test", "bonjour"));
 *		System.out.println("Getting the string ...");
 *		HeapItem hi = h.get("test",0);
 *		System.out.println(hi.getString());
 *
 */

import java.util.HashMap;

public class Heap {
	private HashMap<Integer, Context> heap;
	
	public Heap() {
		heap = new HashMap<Integer, Context>();
		
		// Create global context
		newContext(0);
	}
	
	public void newContext(int context_id) {
		Context ctx = new Context(context_id);
		heap.put(context_id, ctx);
	}
	
	public Context getContext(int context_id) {
		return heap.get(context_id);
	}
	
	public HeapItem get(int key, int context_id) {
		if (!hasContext(context_id)) return null;
		
		return heap.get(context_id).get(key);
	}
	
	public HeapItem get(String key, int context_id) {
		if (!hasContext(context_id)) return null;
		
		return heap.get(context_id).get(key);
	}
	
	public boolean hasContext(int context_id) {
		return heap.containsKey(context_id);
	}
	
	public boolean hasItem(int key, int context_id) {
		if (!hasContext(context_id)) return false;
		
		return heap.get(context_id).containsKey(key);
	}
}
