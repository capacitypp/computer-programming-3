import java.util.HashSet;

class SetTest {
	public static void main(String[] args) {
		HashSet intSet = new HashSet();
		intSet.add(100);
		intSet.add(100);
		intSet.add(500);
		intSet.add(500);
		intSet.add(800);
		intSet.add(800);

		System.out.println(intSet.size());
		System.out.println(intSet.contains(500));
		System.out.println(intSet.contains(300));

		intSet.remove(500);

		System.out.println(intSet.size());
		System.out.println(intSet.contains(500));
		System.out.println(intSet.contains(300));

		for (Object element : intSet)
			System.out.println((Integer)element);
	}
}

