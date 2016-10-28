public class Main2 {
	public static void main(String[] args) {
		System.out.println("Testing WebService, hit CTRL+C to exit.");
		WebService2 service = new WebService2();
		new UserThread2(service, "Akira", 'A').start();
		new UserThread2(service, "Bobby", 'B').start();
		new UserThread2(service, "Christine", 'C').start();
	}
}

