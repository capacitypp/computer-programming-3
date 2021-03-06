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
	public int[] analyze(String query[]) {
		int[] results = new int[evalLabel.length];

		ArrayList<Integer> list = new ArrayList<Integer>();
		/* コードの簡略化のため、初めはORとする */
		LogicalMode mode = LogicalMode.OR;
		for (String string : query) {
			/* 現在の検索結果と次の検索結果をANDで統合する */
			if (string.equals("and")) {
				mode = LogicalMode.AND;
				continue;
			}
			/* 現在の検索結果と次の検索結果をORで統合する */
			if (string.equals("or")) {
				mode = LogicalMode.OR;
				continue;
			}
			/* 項目と条件の切り出し */
			String[] tokens = string.split("=");
			/* 問い合わせ */
			ArrayList<Integer> ret = search(tokens[0], tokens[1]);
			/* 今までの検索結果との統合 */
			if (mode == LogicalMode.AND)
				list = and(list, ret);
			else
				list = or(list, ret);
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

		return results;
	}

	public static void main(String[] args) {
		Car car = new Car("car.csv");
		int evals[] = car.analyze(args);
		for (int i = 0; i < evals.length; i++)
			System.out.printf("%s = %d,\n", evalLabel[i], evals[i]);
	}
}

