public class Main3 {
	public static void main(String[] args) {
		System.out.println("Testing WebService, hit CTRL+C to exit.");
		WebService service = new WebService();
		Thread userA = new Thread(new UserThread(service, "Akira", 'A'));
		userA.start();
		Thread userB = new Thread(new UserThread(service, "Bobby", 'B'));
		userB.start();
		Thread userC = new Thread(new UserThread(service, "Christine", 'C'));
		userC.start();
	}
}

