package util;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class File {

	public static void write(String filename, String content) {
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8");
			outputStreamWriter.write(content);
			outputStreamWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
