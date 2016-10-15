class ArrayTest {
	public static void main(String[] args) {
		int intArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		float floatArray[] = new float[10];
		for (int i = 0; i < floatArray.length; i++)
			floatArray[i] = 10.f * ((float)i + 1.0f);

		String stringArray[] = new String[5];
		for (int s = 0; s < stringArray.length; s++)
			stringArray[s] = new String("StringValueOf int:" + intArray[s] + " and float:" + floatArray[s]);

		for (String str : stringArray)
			System.out.println(str);
	}
}

