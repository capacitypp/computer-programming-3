import java.io.*;
import java.util.*;

public class Result extends HashMap<String, Integer> implements Serializable {
	static void write(Result result, String fpath) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fpath));
			oos.writeObject(result);
		}
		catch (IOException e) {
			System.out.println("function write() failed.");
		}
	}

	static Result read(String fpath) {
		Result result = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fpath));
			result = (Result)ois.readObject();
		}
		catch (IOException e) {
			System.out.println("function write() failed.");
		}
		catch (ClassNotFoundException e) {
			System.out.println("function write() failed.");
		}
		return result;
	}
}

