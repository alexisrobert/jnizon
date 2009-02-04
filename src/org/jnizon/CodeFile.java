package org.jnizon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.runtime.RecognitionException;

public class CodeFile {

	private File file;

	public CodeFile(File file) {
		this.file = file;
	}

	public CodeFile(String filename) {
		file = new File(filename);
	}

	public File getFile() {
		return file;
	}

	public String getFilename() {
		return file.getAbsolutePath();
	}

	public void evaluate(Interpreter interpreter) throws IOException,
			RecognitionException {
		FileReader reader = new FileReader(file);
		BufferedReader buf = new BufferedReader(reader);
		StringBuffer buffer = new StringBuffer((int) file.length());
		String line = null;
		do {
			line = buf.readLine();
			buffer.append(line);
		} while (line != null);
		interpreter.evaluate(buffer.toString());

		buf.close();
		reader.close();
	}

}
