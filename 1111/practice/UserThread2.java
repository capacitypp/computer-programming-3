public class UserThread2 extends Thread {
	private final WebService2 service;
	private final String myname;
	private final char mybutton;

	public UserThread2(WebService2 service, String myname, char mybutton) {
		this.service = service;
		this.myname = myname;
		this.mybutton = mybutton;
	}

	public void run() {
		System.out.println(myname + " BEGIN");
		while (true)
			service.clickResponse(myname, mybutton);
	}
}

