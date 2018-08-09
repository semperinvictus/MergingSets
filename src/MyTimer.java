
public class MyTimer {

	private long start = -1;
	
	public MyTimer() {}
	
	public void start() {
		start = System.currentTimeMillis();
	}
	
	// time in milliseconds
	public int stop() {
		if (start == -1) {
			throw new RuntimeException("Error -- Timer was stopped without being restarted!");
		}
		final int ms = (int) ((System.currentTimeMillis() - start));
		start = -1;
		return ms;
	}
	
}
