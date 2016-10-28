public class Main6 {
	public static void main(String[] args) {
		Table2 table = new Table2(3);
		new MakerThread2("MakerThread-1", table).start();
		new MakerThread2("MakerThread-2", table).start();
		new MakerThread2("MakerThread-3", table).start();
		new EaterThread2("EaterThread-1", table).start();
		new EaterThread2("EaterThread-2", table).start();
		new EaterThread2("EaterThread-3", table).start();
	}
}

