package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
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

	public static String read(String filename) {
		try {
			StringBuffer buffer = new StringBuffer();
			InputStreamReader onputStreamReader = new InputStreamReader(new FileInputStream(filename), "UTF-8");
			char[] chars = new char[1024];
			for (int len = onputStreamReader.read(chars); len != -1; len = onputStreamReader.read(chars))
				buffer.append(chars, 0, len);
			onputStreamReader.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
