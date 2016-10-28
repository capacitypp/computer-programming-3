public class WebService2 {
	private int counter = 0;
	private String name = "Nobody";
	private char button = 'N';

	public synchronized void clickResponse(String name, char button) {
		counter++;
		this.name = name;
		this.button = button;
		check();
	}

	public synchronized String toString() {
		return "No." + counter + ": " + name + ", " + button;
	}

	private void check() {
		if (name.charAt(0) != button)
			System.out.println("***** BROKEN ***** " + toString());
	}
}

