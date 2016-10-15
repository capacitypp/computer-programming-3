public class Mutable {
	private String message;

	public Mutable(String message) {
		this.message = message;
	}

	public void hello() {
		System.out.println(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

