public final class Immutable {
	private final String message;
	
	public Immutable(String message) {
		this.message = message;
	}

	public void hello() {
		System.out.println(message);
	}
}

