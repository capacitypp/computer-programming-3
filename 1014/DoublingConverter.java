public class DoublingConverter extends AbstractConverter {
	@Override
	protected String computeStringToAppend(int c) {
		StringBuilder sb = new StringBuilder();
		sb.append((char)c);
		sb.append((char)c);
		return sb.toString();
	}

	@Override
	public String getDescription() {
		return "文字をダブらせます";
	}
}

