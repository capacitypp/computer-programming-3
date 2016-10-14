public class ToLowerConverter extends AbstractConverter {
	@Override
	protected String computeStringToAppend(int c) {
		/* 小文字に変換して返す */
		return String.valueOf(Character.toLowerCase((char)c));
	}

	@Override
	public String getDescription() {
		return "すべて小文字に変換します";
	}
}

