package net.richstudios.ub.util.file;

public class ParseException extends Exception {
	private static final long serialVersionUID = -3128115359403153600L;
	
	public ParseException(String path) {
		super("Could not parse file: " + path);
	}

}
