import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("講義名", "ソフトウェア演習");
		map.put("場所", "F101");
		map.put("実施日", "金曜日");
		map.put("PC", "iMac");

		System.out.println(map.size());
		System.out.println(map.containsKey("PC"));
		System.out.println(map.containsKey("iMac"));
		System.out.println(map.get("試験日"));
		System.out.println(map.get("実施日"));
	}
}

