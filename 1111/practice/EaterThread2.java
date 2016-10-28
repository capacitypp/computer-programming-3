import java.util.Random;

public class EaterThread2 extends Thread {
	private final Random random;
	private final Table2 table;

	public EaterThread2(String name, Table2 table) {
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

