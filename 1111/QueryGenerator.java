
import java.util.*;

public class QueryGenerator {
    private static String labels [] = {"buying","maint","doors","persons","luggage","safety"};
	private static String values[][] = { {"vhigh", "high", "med", "low"},
		{"vhigh", "high", "med", "low"}, 
		{"2", "3", "4", "5more"},
		{"2", "4", "more"}, 
		{"small", "med", "big"},
		{"low", "med", "high"} };
    
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

	public static void func(Car car, String[] query) {

		String[] evalLabel = {"unacc", "acc", "good", "vgood"};
		int[] evals = car.analyze(query);
		for (int i = 0; i < evalLabel.length; i++)
			System.out.println(evalLabel[i] + " : " + evals[i]);
		car.delete(query);
	}
    
    public static void main(String[] args) {
    	QueryGenerator queryGen = new QueryGenerator();
    	HashMap<String,String> condition = queryGen.getCondition ();

		ArrayList<String> queryArrayList = new ArrayList<String>();
    	for (Map.Entry<String, String> entry : condition.entrySet()) {
			if (queryArrayList.size() != 0)
				queryArrayList.add("and");
			queryArrayList.add(entry.getKey() + "=" + entry.getValue());
		}
		String[] query = queryArrayList.toArray(new String[0]);
		for (String string : query)
			System.out.print(string + " ");
		System.out.println();

		Car car = new Car("car.csv");

		func(car, query);
		func(car, query);
    }
}
