package org.jnizon;

public class JNizon {

	/**
	 * @param args
	 * @throws RecognitionException
	 */
	public static void main(String[] args) {
		Interpreter it = new Interpreter();
		Shell sh = new Shell(it);
		sh.start();
	}
}
