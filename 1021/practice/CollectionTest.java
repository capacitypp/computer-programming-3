import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class CollectionTest {
	public static void main(String[] args) {
		ArrayList<String> alist = new ArrayList<String>();
		alist.add("foo");
		alist.add("bar");
		TreeSet<String> tree = new TreeSet<String>();
		tree.add("nodeA");
		tree.add("nodeB");

		CollectionTest test = new CollectionTest();
		test.printStatus(alist);
		test.printStatus(tree);

		Iterator<String> itr = alist.iterator();
		while (itr.hasNext())
			System.out.println(itr.next());
		itr = tree.iterator();
		while (itr.hasNext())
			System.out.println(itr.next());
	}

	private void printStatus(Collection<String> collection) {
		System.out.println("size = " + collection.size());
		StringBuilder sb = new StringBuilder();
		sb.append("element = ");
		for (Object element : collection)
			sb.append(element).append(" ");
		System.out.println(sb.toString());
	}
}

