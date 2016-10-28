public class Main4 {
	public static void main(String[] args) {
		Data data = new Data(10);

		new ReaderThread(data, 1).start();
		new ReaderThread(data, 2).start();
		new ReaderThread(data, 3).start();
		new ReaderThread(data, 4).start();
		new ReaderThread(data, 5).start();
		new WriterThread(data, "ABCDEFGHIJKLMNOPQTSTUVWXYZ").start();
		new WriterThread(data, "abcdefghijklmnopqrstuvwxyz").start();
	}
}

