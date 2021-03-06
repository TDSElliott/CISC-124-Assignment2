package assignment2;

public class Song {
	private String title = "";
	private int aspect1 = -2;
	private int aspect2 = -2;
	private int aspect3 = -2;
	private int aspect4 = -2;
	private int aspect5 = -2;
	private int aspect6 = -2;
	
	private int closestCategory;
	
	public Song() {
		
	}
	
	public Song(String id, int a1, int a2, int a3, int a4, int a5, int a6) {
		title = id;
		aspect1 = a1;
		aspect2 = a2;
		aspect3 = a3;
		aspect4 = a4;
		aspect5 = a5;
		aspect6 = a6;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getAspect(String aspect) {
		if (aspect.equals("a1")) {
			return aspect1;
		} else if (aspect.equals("a2")) {
			return aspect2;
		} else if (aspect.equals("a3")) {
			return aspect3;
		} else if (aspect.equals("a4")) {
			return aspect4;
		} else if (aspect.equals("a5")) {
			return aspect5;
		} else if (aspect.equals("a6")) {
			return aspect6;
		} else {
			return -1;
		}
	}
	
	public int[] getAspect() {
		int[] allAspects = {aspect1, aspect2, aspect3, aspect4, aspect5, aspect6};
		return allAspects;
	}
	
	public void setCategory(int category) {
		closestCategory = category;
	}
	
	public int getCategory() {
		return closestCategory;
	}
}
