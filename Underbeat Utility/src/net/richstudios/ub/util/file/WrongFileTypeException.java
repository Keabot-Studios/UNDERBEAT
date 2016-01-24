package net.richstudios.ub.util.file;

public class WrongFileTypeException extends Exception {
	private static final long serialVersionUID = 8576893376249691725L;
	
	public WrongFileTypeException(String given, String expected) {
		super("Wrong file type! Given: " + given + ", Expected: " + expected);
	}

}
