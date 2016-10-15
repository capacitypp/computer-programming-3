import java.util.*;

public class CollectionSample {
	public static void main(String[] args) {
		ArrayList list1 = new ArrayList();
		LinkedList list2 = new LinkedList();
		HashSet set1 = new HashSet();
		TreeSet set2 = new TreeSet();
		LinkedHashSet set3 = new LinkedHashSet();

		for (String arg : args) {
			list1.add(arg);
			list2.add(arg);
			set1.add(arg);
			set2.add(arg);
			set3.add(arg);
		}
		System.out.println("ArrayList = " + list1);
		System.out.println("LinkedList = " + list2);
		System.out.println("HashSet = " + set1);
		System.out.println("TreeSet = " + set2);
		System.out.println("LinkedHashSet = " + set3);
	}
}

