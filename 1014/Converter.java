import java.io.IOException;

public interface Converter {
	public String convert(String source) throws IOException;
	public String getDescription();
}

