package assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Classifier {
	
	
	static BufferedReader br;
	static BufferedWriter bw;
	
	
	static ArrayList<Category> categories = new ArrayList<Category>();
	static ArrayList<Song> songs = new ArrayList<Song>();
	static ArrayList<Error> errors = new ArrayList<Error>();
	
	public static void main(String[] args) {
		// First we read in the categories, and songs
		// So we have a dataset to work with
		readCategories();
		readSongs();
		
		// Run through every song and compare it to all categories
		checkDistance();
		
		// At this point all categorizing work is done, time to output
		saveCategoryInfo();
		saveSongInfo();
		
		// This is also output, but it writes things that have mistakes rather than successes
		dumpErrors();
	}
	
	/* ***************************************
	 * 										 *
	 * Warning: Math below this point! 		 *
	 * 										 *
	 * ***************************************/
	
	/**
	 * Check Distance - computes the distance between each song and all categories
	 * Adds the info of closest category to the Song object and increments the category's count
	 */
	public static void checkDistance() {
		for (int x = 0; x < songs.size(); x++) {
			int lowestDistance = 999999999; // use this to track which category is closest
			int closeCategoryID = 999999999; // use to store id of closest category
			int[] songAspects = songs.get(x).getAspect();
			for (int y = 0; y < categories.size(); y++) {
				int[] categoryAspects = categories.get(y).getAspect();
				
				int distance = 0;
				
				for(int z = 0; z < 6; z++) {
					distance += (songAspects[z] - categoryAspects[z]) * (songAspects[z] - categoryAspects[z]);
				}
				
				if (distance < lowestDistance) {
					closeCategoryID = categories.get(y).getID();
					lowestDistance = distance;
				} else if ((distance == lowestDistance) && (categories.get(y).getID() < closeCategoryID)) {
						closeCategoryID = categories.get(y).getID();
						lowestDistance = distance;
				}
				
				if (distance < categories.get(y).getClosestDistance()) {
					categories.get(y).setClosestDistance(distance);
					categories.get(y).setClosestSongTitle(songs.get(x).getTitle());
				}
			}
			songs.get(x).setCategory(closeCategoryID);
			
			for (int a = 0; a < categories.size(); a++) {
				if(categories.get(a).getID() == closeCategoryID) {
					categories.get(a).incrementNumSongs();
				}
			}
		}
	}
	
	
	
	/* ***************************************
	 * 										 *
	 * Warning: File input below this point! *
	 * 										 *
	 * ***************************************/
	
	/**
	 * Read Categories - reads in lines from categories.txt and splits them to pass as parameters into new Category objects
	 */
	public static void readCategories() {
		try {
			br = new BufferedReader(new FileReader("categories.txt"));
			String line = br.readLine();
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
	
	/**
	 * Read Songs - reads in lines from songs.txt and splits them to pass as parameters into new Song objects
	 */
	public static void readSongs() {
		try {
			br = new BufferedReader(new FileReader("songs.txt"));
			String line = br.readLine();
			while (line != null) {
				String[] split = line.split(",");
				
				if (split.length <= 6) {
					errors.add(new Error(line, "(not enough aspect values)"));
				} else if (split.length > 8) {
					errors.add(new Error(line, "(too many aspect values)"));
				} else if (split.length == 8) {
					try {
						int a1 = Integer.parseInt(split[1]);
						errors.add(new Error(line,"(too many aspect values)"));	
					} catch (NumberFormatException e) {
						errors.add(new Error(line,"(comma in title)"));
					}
					
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* ***************************************
	 * 										 *
	 * Warning: File output below this point!*
	 * 										 *
	 * ***************************************/
	
	/**
	 * Dump Errors - write the contents of the error Arraylist, which stores faulty song input, to a .txt file
	 */
	public static void dumpErrors() {
		try {
			OutputStreamWriter output = new OutputStreamWriter(
			            new FileOutputStream("errors.txt"), "UTF-8");

	 		bw = new BufferedWriter(output);
		    for (int x = 0; x < errors.size(); x++) {
		    	bw.write(errors.get(x).getErrorInfo());
		    	bw.newLine();
		    }
		    bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save Category Info - write the contents of the category Arraylist, which stores the input cactegories, to a .txt file
	 */
	public static void saveCategoryInfo() {
		try {
			 OutputStreamWriter output = new OutputStreamWriter(
			            new FileOutputStream("category_stats.txt"), "UTF-8");

		    bw = new BufferedWriter(output);
		    for (int x = 0; x < categories.size(); x++) {
		    	bw.write(categories.get(x).getID() + "," + categories.get(x).getNumSongs() + "," + categories.get(x).getClosestSongTitle());
		    	bw.newLine();
		    }
		    bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save Song Info - write the contents of the song Arraylist, which stores the input songs, to a .txt file
	 */
	public static void saveSongInfo() {
		try {
			 OutputStreamWriter output = new OutputStreamWriter(
			            new FileOutputStream("song_category.txt"), "UTF-8");

			bw = new BufferedWriter(output);
		    for (int x = 0; x < songs.size(); x++) {
		    	bw.write(songs.get(x).getTitle() + "," + songs.get(x).getCategory());
		    	bw.newLine();
		    }
		    bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
