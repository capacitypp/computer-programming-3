import java.io.IOException;
import java.io.StringReader;

public abstract class AbstractConverter implements Converter {
	public String convert(String source) throws IOException {
		StringBuilder sb = new StringBuilder();
		StringReader reader = new StringReader(source);

		int c;
		while ((c = reader.read()) != -1) {
			String str = computeStringToAppend(c);
			if (str != null) {
				sb.append(str);
			}
		}
		return sb.toString();
	}
	protected abstract String computeStringToAppend(int c);
}

