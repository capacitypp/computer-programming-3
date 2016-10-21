import java.io.*;
import java.util.*;

/* Carクラスにおける検索結果クラス */
public class Result extends HashMap<String, Integer> implements Serializable {
	/* ファイルへの書き込み */
	static void write(Result result, String fpath) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fpath));
			oos.writeObject(result);
		}
		catch (IOException e) {
			System.out.println("function write() failed.");
		}
	}

	/* ファイルからの読み込み */
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

