public class Data {
	private final char[] buffer;
	private final ReadWriteLock lock = new ReadWriteLock();

	public Data(int size) {
		buffer = new char[size];
		for (int i = 0; i < buffer.length; i++)
			buffer[i] = '*';
	}

	public char[] read(int n) throws InterruptedException {
		lock.readLock();
		try {
			return doRead(n);
		}
		finally {
			lock.readUnlock();
		}
	}

	public void write(char c) throws InterruptedException {
		lock.writeLock();
		try {
			doWrite(c);
		}
		finally {
			lock.writeUnlock();
		}
	}

	private char[] doRead(int n) {
		char [] newbuf = new char[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			if (i < n)
				newbuf[i] = buffer[i];
			else {
				String digit = String.valueOf(n);
				newbuf[i] = digit.charAt(0);
			}
			slowly();
		}
		return newbuf;
	}

	private void doWrite(char c) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			slowly();
		}
	}

	private void slowly() {
		try {
			Thread.sleep(50);
		}
		catch (InterruptedException e) {

		}
	}
}

