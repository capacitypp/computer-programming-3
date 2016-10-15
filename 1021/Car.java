import java.util.*;

public class Car extends ArrayList<HashMap<String, String>> {
	static String keys[] = {"buying", "maint", "doors", "persons", "luggage", "safety", "eval"};
	static String evalLabel[] = {"unacc", "acc", "good", "vgood"};

	public Car(String fileName) {

	}

	public int[] analyze(String query[]) {
		int results[] = new int[evalLabel.length];

		return results;
	}

	static void main(String[] args) {
		Car car = new Car("car.csv");
		int evals[] = car.analyze(args);
		for (int i = 0; i < evals.length; i++)
			System.out.printf("%s = %d,\n", evalLabel[i], evals[i]);
	}
}

