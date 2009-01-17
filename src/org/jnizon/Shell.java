package org.jnizon;

import java.io.IOException;

import jline.ConsoleReader;

public class Shell {
	public Shell() {
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
				System.out.print(String.format("Out[%d]:= ",iteration));
				System.out.println(String.format("Will analyse %s !", line));
				
				System.out.println();
				iteration++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}