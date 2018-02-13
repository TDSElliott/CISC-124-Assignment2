package assignment2;

public class Error {
	private String songInfo = "";
	private String error = "";
	
	/**
	 * It's good practice to make a no-argument constructor so an object can easily be generated
	 */
	public Error() {
		
	}
	
	public Error(String sI, String e) {
		songInfo = sI;
		error = e;
	}
	
	public String getErrorInfo() {
		return songInfo + " " + error;
	}
}
