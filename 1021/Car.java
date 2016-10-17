import java.util.*;

public class Car extends ArrayList<HashMap<String, String>> {
	static String keys[] = {"buying", "maint", "doors", "persons", "luggage", "safety", "eval"};
	static String evalLabel[] = {"unacc", "acc", "good", "vgood"};
	static int columnNum = 7;

	enum  LogicalMode {AND, OR};

	private String[][] cars;

	public Car(String fileName) {
		TextFileReader textFileReader = new TextFileReader(fileName);
		ArrayList<String> lines = textFileReader.read();

		cars = new String[lines.size()][];
		for (int i = 0; i < lines.size(); i++)
			cars[i] = lines.get(i).split(",");
	}

	public int[] analyze(String query[]) {
		int[] results = new int[evalLabel.length];

		LogicalMode mode = LogicalMode.OR;
		for (String string : query) {
			if (string.equals("and")) {
				mode = LogicalMode.AND;
				continue;
			}
			if (string.equals("or")) {
				mode = LogicalMode.OR;
				continue;
			}
			String[] tokens = string.split("=");
			for (String token : tokens)
				System.out.println(token);
			System.out.println();
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

