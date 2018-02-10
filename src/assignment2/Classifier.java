package assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Classifier {
	
	static Category[] categories = new Category[10];

	public static void main(String[] args) {
		readCategories();
		readSongs();
//		doSomeMath();
		System.out.println("Hello world");
		
	}
	
	
	public static void readCategories() {
		BufferedReader br;
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
				categories[x] = tempCategory;
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
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("songs.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
