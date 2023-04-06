package LibrarySystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
	private ArrayList<Book> inventory = new ArrayList<Book>();
	private File myFile;
	
	
	public Library() {}
	
	public Library(File myFile) throws FileNotFoundException {
		this.myFile = myFile;
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
	
	public void exportInventory(File myFile) throws FileNotFoundException {
		PrintWriter outputFile = new PrintWriter(myFile);
		for(int i = 0; i < inventory.size(); i++) {
			String str = inventory.get(i).textFormat();
			outputFile.println(str);
		}
		outputFile.close();
	}

	public ArrayList<Book> getInv(){
		return inventory;
	}

	public String setText(){
		String result = "";
		for(int i = 0; i < inventory.size(); i++) {
			result+= "Index " + i + ": " + inventory.get(i).toString() + "\n" + "\n";
		}
		return result;
	}

	private String addBookText(){
		String result = "";
		for(int i = 0; i < inventory.size(); i++) {
			result+= inventory.get(i).textFormat() + "\n";
		}
		return result;
	}
}
