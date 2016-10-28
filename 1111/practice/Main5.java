public class Main5 {
	public static void main(String[] args) {
		Table table = new Table(3);
		new MakerThread("MakerThread-1", table).start();
		new MakerThread("MakerThread-2", table).start();
		new MakerThread("MakerThread-3", table).start();
		new EaterThread("EaterThread-1", table).start();
		new EaterThread("EaterThread-2", table).start();
		new EaterThread("EaterThread-3", table).start();
	}
}

