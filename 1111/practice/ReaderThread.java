public class ReaderThread extends Thread {
	private final Data data;
	private final int dataLength;

	public ReaderThread(Data data, int n) {
		this.data = data;
		this.dataLength = n;
	}

	public void run() {
		try {
			while (true) {
				char[] readbuf = data.read(dataLength);
				System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readbuf));
			}
		}
		catch (InterruptedException e) {

		}
	}
}

