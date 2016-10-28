import java.util.Random;

public class MakerThread extends Thread {
	private final Random random;
	private final Table table;
	private static int id = 0;

	public MakerThread(String name, Table table) {
		super(name);
		this.table = table;
		random  = new Random();
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(random.nextInt(1000));
				String cake = "[ Cake No." + nextId() + " by " + getName() + " ]";
				table.put(cake);
			}
		}
		catch (InterruptedException e) {

		}
	}

	private static synchronized int nextId() {
		return id++;
	}
}

