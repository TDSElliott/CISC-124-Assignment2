package assignment2;

public class Error {
	private String songInfo = "";
	private String error = "";
	
	public Error(String sI, String e) {
		songInfo = sI;
		error = e;
	}
	
	public String getErrorInfo() {
		return songInfo + " " + error;
	}
}
