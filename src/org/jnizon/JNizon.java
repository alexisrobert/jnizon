package org.jnizon;

import org.antlr.runtime.RecognitionException;

public class JNizon {

	/**
	 * @param args
	 * @throws RecognitionException 
	 * @throws RecognitionException
	 */
	public static void main(String[] args) throws RecognitionException {
		Interpreter it = new Interpreter();
		Shell sh = new Shell(it);
		sh.start();
	}
}
