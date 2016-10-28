import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileReader {
	FileReader fileReader = null;

	public TextFileReader(String fileName) {
		try {
			fileReader = new FileReader(fileName);
		}
		catch (FileNotFoundException e) {
			System.out.println("ファイルが見つかりません");
		}
	}

	public ArrayList<String> read() {
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		ArrayList<String> lines = new ArrayList<String>();

		try {
			while ((line = bufferedReader.readLine()) != null)
				lines.add(line);
			bufferedReader.close();
		}
		catch (IOException e) {
			System.out.println("入出力エラーが発生しました");
		}

		return lines;
	}

	public void readAndDisplay() {
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		int lineNumber = 0;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				System.out.printf("%2d: %s%n", lineNumber, line);
				lineNumber++;
			}
			bufferedReader.close();
		}
		catch (IOException e) {
			System.out.println("入出力エラーが発生しました");
		}
	}

	public static void main(String[] args) {
		TextFileReader tfReader = new TextFileReader("car.csv");
		if (tfReader != null)
			tfReader.readAndDisplay();
	}
}

