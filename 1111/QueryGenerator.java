
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
    
    public static void main(String[] args) {
    	QueryGenerator queryGen = new QueryGenerator();
    	HashMap<String,String> condition = queryGen.getCondition ();
    	for (Map.Entry<String, String> entry : condition.entrySet())
    		System.out.println("Condition = " + entry.getKey() + " : " + entry.getValue());
    }
}