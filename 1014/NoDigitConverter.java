public class NoDigitConverter extends AbstractConverter {
	@Override
	protected String computeStringToAppend(int c) {
		StringBuilder sb = new StringBuilder();
		/* 数字であれば空文字を返す */
		if (Character.isDigit(c))
			return "";
		return String.valueOf((char)c);
	}

	@Override
	public String getDescription() {
		return "数字を除去します";
	}
}

