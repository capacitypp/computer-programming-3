
import java.util.*;

public class QueryGenerator extends Thread {
    private static String labels [] = {"buying","maint","doors","persons","luggage","safety"};
	private static String values[][] = { {"vhigh", "high", "med", "low"},
		{"vhigh", "high", "med", "low"}, 
		{"2", "3", "4", "5more"},
		{"2", "4", "more"}, 
		{"small", "med", "big"},
		{"low", "med", "high"} };

	private int id;
	private Car car;
	private int count;

	public QueryGenerator(int id, Car car) {
		this.id = id;
		this.car = car;
		count = 0;
	}
    
    private int randomWithRange(int range) {
        return (int)Math.floor(Math.random() * range);
    }

    public HashMap<String,String> getCondition () {
    	HashMap<String,String> condition = new HashMap<String,String>();
        int numLabel = randomWithRange (labels.length);
        int numValue = randomWithRange (values[numLabel].length);
        condition.put(labels[numLabel] , values[numLabel][numValue]);
        int prevNumLabel = numLabel;
        do {
        	numLabel = randomWithRange (labels.length);
        	numValue = randomWithRange (values[numLabel].length);
        } while (numLabel == prevNumLabel);
        condition.put(labels[numLabel] , values[numLabel][numValue]);
        return condition;
    }

	public String[] getQuery() {
    	HashMap<String,String> condition = getCondition ();

		ArrayList<String> queryArrayList = new ArrayList<String>();
    	for (Map.Entry<String, String> entry : condition.entrySet()) {
			if (queryArrayList.size() != 0)
				queryArrayList.add("and");
			queryArrayList.add(entry.getKey() + "=" + entry.getValue());
		}
		return queryArrayList.toArray(new String[0]);
	}

	/*
	public static void func(Car car, String[] query) {

		String[] evalLabel = {"unacc", "acc", "good", "vgood"};
		int[] evals = car.analyze(query);
		for (int i = 0; i < evalLabel.length; i++)
			System.out.println(evalLabel[i] + " : " + evals[i]);
		car.delete(query);
	}
	*/

	public double score(int[] evals) {
		int scoreSum = 0;
		int sum = 0;
		for (int i = 0; i < evals.length; i++) {
			scoreSum += i * evals[i];
			sum += evals[i];
		}
		if (sum == 0)
			sum = 1;
		return (double)scoreSum / sum;
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
		/*
		String[] query = getQuery();
		for (String string : query)
			System.out.print(string + " ");
		System.out.println();

		Car car = new Car("car.csv");

		func(car, query);
		func(car, query);
		*/
		Car car = new Car("car.csv");
		for (int i = 1; i <= 5; i++)
			new QueryGenerator(i, car).start();
    }
}
