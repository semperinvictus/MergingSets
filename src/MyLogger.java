import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class MyLogger {
	
	public enum LVL {
		SEVERE
		, WARN
		, INFO
	}

	private static MyLogger logger = null;
	private String filename = "log.txt";
	
	public static MyLogger instance() {
		if (logger == null) {
			return new MyLogger();
		} else {
			return logger;
		}
	}
	
	public MyLogger instance(String filename) {
		this.filename = filename;
		return instance();
	}
	
	public void log(String msg, LVL lvl) {
		try (
				FileWriter fw = new FileWriter(filename, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)
			) {
			out.println("|"+lvl.name().toUpperCase()+"| " + msg);
		} catch (IOException e) {
			throw new RuntimeException("Failed on file operation", e);
		}
	}

}
