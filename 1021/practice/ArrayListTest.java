import java.util.ArrayList;

class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<String> strList = new ArrayList<String>();
		strList.add("foo");
		strList.add("foo");
		strList.add("bar");
		strList.add("bar");
		strList.add("baz");
		strList.add("baz");

		String one = strList.get(1);
		System.out.println(one);
		System.out.println(strList.size());
		System.out.println(strList.contains("foo"));
		System.out.println(strList.contains("xxx"));

		strList.remove(0);
		System.out.println(strList.get(1));
		System.out.println(strList.size());
		System.out.println(strList.contains("foo"));
		System.out.println(strList.contains("xxx"));
		
		for (String element : strList)
			System.out.println(element);
	}
}

