package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileTool {
	public static void save(Object object, String path) {
		try {
			FileOutputStream fOutputStream = new FileOutputStream(path);
			ObjectOutputStream outputStream = new ObjectOutputStream(fOutputStream);
			outputStream.writeObject(object);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Object load(String path) {
		FileInputStream fInputStream;
		Object object = null;
		try {
			fInputStream = new FileInputStream(path);
			ObjectInputStream inputStream = new ObjectInputStream(fInputStream);
			object = inputStream.readObject();
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
		
	}
}
