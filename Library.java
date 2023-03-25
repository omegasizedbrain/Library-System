package LibrarySystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
	ArrayList<Book> inventory = new ArrayList<Book>();
	
	
	public Library() {}
	
	public Library(File myFile) throws FileNotFoundException {
		setInventory(myFile);
	}
	
	public void addToInventory() {
		
	}
	
	public void setInventory(File myFile) throws FileNotFoundException {
		Scanner inputFile = new Scanner(myFile);
		while(inputFile.hasNext())
		{
			String str = inputFile.nextLine();
			String[] list = str.split("/");
			Book b = new Book(list);
			inventory.add(b);
		}
		inputFile.close();
	}
	
	public void printBook(int i) {
		System.out.println(inventory.get(i));
	}
	
	public void exportinventory(File myFile) throws FileNotFoundException {
		PrintWriter outputfile = new PrintWriter(myFile);
		for(int i = 0; i < inventory.size(); i++) {
			String str = inventory.get(i).textFormat();
			outputfile.println(str);
		}
		outputfile.close();
	}
	
	public ArrayList<Book> search(String criteria, String field) {
		ArrayList<String> compare = new ArrayList<String>();
		ArrayList<Book> hits = new ArrayList<Book>();
		System.out.println("switching " + field.toLowerCase() + " and " + criteria.toLowerCase());
		switch(field.toLowerCase()) {
		case "author":
			inventory.forEach((b) -> compare.add(b.getAuthor()));
			break;
		case "volume number":
			inventory.forEach((b) -> compare.add(b.getVolumeNumber()));
			break;
		case "title":
			inventory.forEach((b) -> compare.add(b.getTitle()));
			break;
		case "genre":
			inventory.forEach((b) -> compare.add(b.getGenre()));
			break;
		case "shelf number":
			inventory.forEach((b) -> compare.add(b.getShelfNumber()));
			break;
		}
		for(int i = 0; i < compare.size(); i++) {
			String thing = compare.get(i).toLowerCase();
			System.out.println("Comparing " + thing + " and " + criteria.toLowerCase() + " ::: are equal?: " + thing.equals(criteria.toLowerCase()));
			if(thing.equals(criteria.toLowerCase())) {
				System.out.println("adding " + thing);
				hits.add(inventory.get(i));
			}
		}
		return hits;
	}

	public ArrayList<Book> getInv(){
		return inventory;
	}
}
