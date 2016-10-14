import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Processor {
	public void run(Converter converter) throws IOException {
		/* クラスの説明を表示する */
		System.out.println(converter.getDescription());

		System.out.println("入力");

		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufr = new BufferedReader(istream);
		/* 1行分読み込む */
		String source = bufr.readLine();

		StringBuilder sb = new StringBuilder();
		StringReader reader = new StringReader(source);
		int c;

		while ((c = reader.read()) != -1)
			sb.append((char)c);
		/* 文字列を変換する */
		String result = converter.convert(sb.toString());

		System.out.println(result);
	}

	public static void main(String[] args) throws IOException {
		Converter converter = null;
		/* 1つ目のコマンドライン引数の値に基づいて、インスタンスを生成する */
		switch (Integer.valueOf(args[0])) {
		case 0:
			converter = new DoublingConverter();
			break;
		case 1:
			converter = new NoDigitConverter();
			break;
		case 2:
			converter = new ToLowerConverter();
			break;
		default:
			converter = new NoConverter();
			break;
		}
		new Processor().run(converter);
	}
}

