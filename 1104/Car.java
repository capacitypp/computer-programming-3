import java.util.*;

public class Car extends ArrayList<HashMap<String, String>> {
	static String keys[] = {"buying", "maint", "doors", "persons", "luggage", "safety", "eval"};
	static String evalLabel[] = {"unacc", "acc", "good", "vgood"};
	static int columnNum = 7;

	/* 問い合わせにおける、論理演算の種類 */
	enum  LogicalMode {AND, OR};

	/* 与えられたファイル名のファイルからデータを読み込み、HashMapを生成する */
	public Car(String fileName) {
		TextFileReader textFileReader = new TextFileReader(fileName);
		ArrayList<String> lines = textFileReader.read();

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			HashMap<String, String> hashMap = new HashMap<String, String>();

			String[] tokens = line.split(",");
			for (int j = 0; j < tokens.length; j++)
				hashMap.put(keys[j], tokens[j]);
			add(hashMap);
		}
	}

	/* 1つの条件による問い合わせを行う */
	private ArrayList<Integer> search(String key, String value) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (int i = 0; i < size(); i++) {
			HashMap<String, String> hashMap = get(i);
			String ret = hashMap.get(key);
			if (!ret.equals(value))
				continue;
			arrayList.add(i);
		}
		return arrayList;
	}

	/* 2つの問い合わせ結果に対して、OR演算を行う */
	private ArrayList<Integer> or(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> list = new ArrayList<Integer>(list1);
		for (int idx : list2)
			if (!list.contains(idx))
				list.add(idx);
		return list;
	}

	/* 2つの問い合わせ結果に対して、AND演算を行う */
	private ArrayList<Integer> and(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int idx : list2)
			if (list1.contains(idx))
				list.add(idx);
		return list;
	}

	/* 検索処理 */
	public Result analyze(HashMap<String, String> query, String logic) {
		int[] results = new int[evalLabel.length];
		Result result = new Result();

		ArrayList<Integer> list = null;
		LogicalMode mode = LogicalMode.OR;
		if ((logic != null) && (logic.equals("and")))
			mode = LogicalMode.AND;
		for (String key : query.keySet()) {
			String value = query.get(key);
			/* 問い合わせ */
			ArrayList<Integer> ret = search(key, value);
			if (list == null)
				list = ret;
			else {
				/* 今までの検索結果との統合 */
				if (mode == LogicalMode.AND)
					list = and(list, ret);
				else
					list = or(list, ret);
			}
		}
		/* 検索結果から出力を得る */
		for (int idx : list) {
			HashMap<String, String> hashMap = get(idx);
			String value = hashMap.get("eval");
			for (int i = 0; i < evalLabel.length; i++) {
				if (!value.equals(evalLabel[i]))
					continue;
				results[i]++;
				break;
			}
		}

		for (int i = 0; i < evalLabel.length; i++)
			result.put(evalLabel[i], results[i]);

		return result;
	}
}

