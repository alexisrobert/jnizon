package org.jnizon;

import java.io.IOException;

import org.antlr.runtime.RecognitionException;

import jline.ConsoleReader;

public class Shell {
	private Interpreter interpreter;
	public Shell(Interpreter interpreter) {
		this.interpreter = interpreter;
	}
	
	public void start() {
		System.out.println("Welcome to jizon for Java");
		System.out.println("Copyright (c) 2009 Blumberg & Robert Research Labs Inc.");
		System.out.println("");
		
		ConsoleReader reader;
		String line;
		int iteration = 1;
		
		try {
			reader = new ConsoleReader();
			reader.setUseHistory(true);
			
			while ((line = reader.readLine(String.format("In[%d]:= ",iteration))) != null) {
				System.out.print(String.format("Out[%d]= ",iteration));
				
				try {
					System.out.println(interpreter.evaluate(line));
				} catch (RecognitionException e) {
					e.printStackTrace();
				}
				
				System.out.println();
				iteration++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}