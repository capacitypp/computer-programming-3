public class UserThread extends Thread {
	private final WebService service;
	private final String myname;
	private final char mybutton;

	public UserThread(WebService service, String myname, char mybutton) {
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

