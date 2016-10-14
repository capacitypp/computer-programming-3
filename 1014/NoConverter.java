public class NoConverter extends AbstractConverter {
	@Override
	protected String computeStringToAppend(int c) {
		/* 変換せずにそのままString型にして返す */
		return String.valueOf((char)c);
	}

	@Override
	public String getDescription() {
		return "何も変換しません";
	}
}

