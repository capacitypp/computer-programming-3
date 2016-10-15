public class Color {
	public int red;
	public int green;
	public int blue;

	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color)obj;
		if (blue != other.blue || green != other.green || red != other.red)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return ((red & 0xff) << 16) | ((green & 0xff) << 8) | (blue & 0xff);
	}

	public static void main(String[] args) {
		Color red = new Color(255, 255, 0);
		Color green = new Color(0, 255, 0);
		Color otherGreen = new Color(0, 255, 0);
		System.out.println(red.equals(green));
		System.out.println(green.equals(otherGreen));
		System.out.println(red.hashCode() + ", " + green.hashCode() + ", " + otherGreen.hashCode());
	}
}

