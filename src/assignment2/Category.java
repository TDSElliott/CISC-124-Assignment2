package assignment2;

public class Category {
	
	int identifier = 0;
	int aspect1 = 0;
	int aspect2 = 0;
	int aspect3 = 0;
	int aspect4 = 0;
	int aspect5 = 0;
	int aspect6 = 0;
	

	public Category() {
		
	}
	
	public Category(int id, int a1, int a2, int a3, int a4, int a5, int a6) {
		identifier = id;
		aspect1 = a1;
		aspect2 = a2;
		aspect3 = a3;
		aspect4 = a4;
		aspect5 = a5;
		aspect6 = a6;
		
	}
	
	public int getID() {
		return identifier;
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
}
