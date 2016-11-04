import java.util.*;

public class CarThread extends Thread {
	private int id;		/* スレッド番号 */
	private Car car;
	private int count;	/* 削除を実行した回数 */

	public CarThread(int id, Car car) {
		this.id = id;
		this.car = car;
		count = 0;
	}

	/* 車の平均得点を計算する */
	public double score(int[] evals) {
		int scoreSum = 0;
		int sum = 0;
		for (int i = 0; i < evals.length; i++) {
			scoreSum += i * evals[i];
			sum += evals[i];
		}
		/* 検索条件に該当する車種が存在しなかった場合 */
		if (sum == 0)
			sum = 1;
		return (double)scoreSum / sum;
	}

	/* Querygeneratorで条件を生成し、Carの引数として渡せる形式に変換する */
	public String[] getQuery() {
		QueryGenerator queryGen = new QueryGenerator();
    	HashMap<String,String> condition = queryGen.getCondition ();

		ArrayList<String> queryArrayList = new ArrayList<String>();
    	for (Map.Entry<String, String> entry : condition.entrySet()) {
			if (queryArrayList.size() != 0)
				queryArrayList.add("and");
			queryArrayList.add(entry.getKey() + "=" + entry.getValue());
		}
		return queryArrayList.toArray(new String[0]);
	}

	public void run() {
		try {
			while (count < 3) {
				String[] evalLabel = {"unacc", "acc", "good", "vgood"};
				String[] query = getQuery();
				int[] evals = car.analyze(query);
				double s = score(evals);
				if (s <= 0.5) {
					int sum = 0;
					for (int i = 0; i < evals.length; i++)
						sum += evals[i];
					if (sum == 0)
						continue;
					System.out.println("Thread-" + id + " will delete data !");
					car.delete(query);
					count++;
					/* 条件なしでの検索(全車種がヒットする) */
					evals = car.analyze();
					for (int i = 0; i < evalLabel.length; i++)
						System.out.print(evalLabel[i] + " = " + evals[i] + ", ");
					System.out.println();
				}
				Thread.sleep(1000);
			}
			System.out.println("Thread-" + id + " has finished ...");
		}
		catch (InterruptedException e) {

		}
	}
    
    public static void main(String[] args) {
		Car car = new Car("car.csv");
		for (int i = 1; i <= 5; i++)
			new CarThread(i, car).start();
    }
}

