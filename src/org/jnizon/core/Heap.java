package org.jnizon.core;

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

	private static int lastId = -1;
	private HashMap<Integer, Context> heap;

	public Heap() {
		heap = new HashMap<Integer, Context>();

		// Create global context
		newContext();
	}

	public int newContext() {
		lastId++;
		Context ctx = new Context(lastId, this);
		heap.put(lastId, ctx);
		return lastId;
	}

	public Context getContext(int context_id) {
		return heap.get(context_id);
	}

	public HeapItem get(int key, int context_id) {
		if (!hasContext(context_id))
			return null;

		return heap.get(context_id).get(key);
	}

	public HeapItem get(String key, int context_id) {
		if (!hasContext(context_id))
			return null;

		return heap.get(context_id).get(key);
	}

	public boolean hasContext(int context_id) {
		return heap.containsKey(context_id);
	}

	public boolean hasItem(int key, int context_id) {
		if (!hasContext(context_id))
			return false;

		return heap.get(context_id).containsKey(key);
	}
}
