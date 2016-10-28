public class Main {
	public static void main(String[] args) {
		System.out.println("Testing WebService, hit CTRL+C to exit.");
		WebService service = new WebService();
		new UserThread(service, "Akira", 'A').start();
		new UserThread(service, "Bobby", 'B').start();
		new UserThread(service, "Christine", 'C').start();
	}
}

