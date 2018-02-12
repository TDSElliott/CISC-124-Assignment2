package assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Classifier {
	
	
	static BufferedReader br;
	static BufferedWriter bw;
	
	
	static ArrayList<Category> categories = new ArrayList<Category>();
	static ArrayList<Song> songs = new ArrayList<Song>();
	static ArrayList<Error> errors = new ArrayList<Error>();
	
//	static Category[] categories = new Category[10];

	public static void main(String[] args) {
		readCategories();
		readSongs();
		
		dumpErrors();
		
//		doSomeMath();
		System.out.println("Hello world");
//		for (int x = 0; x < categories.size(); x++ ) {
//			System.out.println(categories.get(x).getID());
//		}
//		
//		for (int x = 0; x < songs.size(); x++ ) {
//			System.out.println(songs.get(x).getTitle());
//		}
		
		for (int x = 0; x < errors.size(); x++ ) {
			System.out.println(errors.get(x).getErrorInfo());
		}
		
	}
	
	/*****************************************
	 * 										 *
	 * Warning: Math below this point! 		 *
	 * 										 *
	 * ***************************************/
	
	// __ use cases
	// run through each song and calculate the distance to every category
	// for the song print out title and category id that is closest (lower num better)
	// for the categories print out id, number of songs that chose this category, song id of closest song
	
	public static void checkDistance() {
		for (int x = 0; x < songs.size(); x++) {
			int[] songAspects = songs.get(x).getAspect();
			for (int y = 0; y < categories.size(); y++) {
				int[] categoryAspects = categories.get(y).getAspect();
				
				
			}
		}
	}
	
	
	
	/*****************************************
	 * 										 *
	 * Warning: File input below this point! *
	 * 										 *
	 * ***************************************/
	
	
	public static void readCategories() {
		try {
			br = new BufferedReader(new FileReader("categories.txt"));
			String line = br.readLine();
			int x = 0; // counts for place in the categories array
			while (line != null) {
				String[] split = line.split(",");
				int id = Integer.parseInt(split[0]);
				int a1 = Integer.parseInt(split[1]);
				int a2 = Integer.parseInt(split[2]);
				int a3 = Integer.parseInt(split[3]);
				int a4 = Integer.parseInt(split[4]);
				int a5 = Integer.parseInt(split[5]);
				int a6 = Integer.parseInt(split[6]);
				Category tempCategory = new Category(id, a1, a2, a3, a4, a5, a6);
				categories.add(tempCategory);
				x++;
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readSongs() {
		try {
			br = new BufferedReader(new FileReader("songs.txt"));
			String line = br.readLine();
			while (line != null) {
				String[] split = line.split(",");
				
				if (split.length <= 6) {
					errors.add(new Error(line, "(not enough aspect values)"));
				} else if (split.length >= 8) {
					errors.add(new Error(line, "(too many aspect values)"));
				} else {
					String title = split[0];
					try {
						int a1 = Integer.parseInt(split[1]);
						try {
							int a2 = Integer.parseInt(split[2]);
							int a3 = Integer.parseInt(split[3]);
							int a4 = Integer.parseInt(split[4]);
							int a5 = Integer.parseInt(split[5]);
							int a6 = Integer.parseInt(split[6]);
							Song tempSong = new Song(title, a1, a2, a3, a4, a5, a6);
							songs.add(tempSong);
						} catch (NumberFormatException e) {
							errors.add(new Error(line, "(one of the aspects is not an integer)"));
						}
					} catch (NumberFormatException e) {
						errors.add(new Error(line, "(comma in title)"));
					}
				}
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*****************************************
	 * 										 *
	 * Warning: File output below this point!*
	 * 										 *
	 * ***************************************/
	public static void dumpErrors() {
		try {
		    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("errors.txt"), "utf-8"));
		    for (int x = 0; x < errors.size(); x++)
		    bw.write(errors.get(x).getErrorInfo());
		} catch (IOException ex) {
		  // report
		}
	}

}
