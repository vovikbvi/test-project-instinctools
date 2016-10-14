package by.instinctools.bogdevich.testing.utils;


public class RandomValue {

	public static final RandomValue INSTANCE = new RandomValue();

	private RandomValue() {
		super();
	}

	public int generateValue() {
		return (int) (Math.random() * 1000000);

	}
}