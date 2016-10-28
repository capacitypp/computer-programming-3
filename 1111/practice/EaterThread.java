import java.util.Random;

public class EaterThread extends Thread {
	private final Random random;
	private final Table table;

	public EaterThread(String name, Table table) {
		super(name);
		this.table = table;
		random = new Random();
	}

	public void run() {
		try {
			while (true) {
				String cake = table.take();
				Thread.sleep(random.nextInt(1000));
			}
		}
		catch (InterruptedException e) {

		}
	}
}

