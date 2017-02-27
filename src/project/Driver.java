package project;

import java.util.Scanner;

public class Driver {
	Scanner scanner;
	FileHandler fileHandler;
	
	public Driver() {
		scanner = new Scanner(System.in);
		fileHandler = new FileHandler();
		menu();
	}

	public static void main(String[] args) {
		new Driver();
	}
	
	private void translate(){
		String userInput;
		System.out.print("Type in a Spanish word: ");
		userInput = scanner.nextLine();
		System.out.println(fileHandler.getDictionary().searchHeapEfficiently(userInput));
		System.out.println();
	}
	
	private void menu(){
		System.out.println("1) Add a word.");
		System.out.println("2) Translate a Spanish word.");
		System.out.println("3) Store the dictionary to file.");
		System.out.println("4) exit.");
		System.out.print("Type in the option and press enter >> ");
		String input = scanner.nextLine();
		
		switch(input){
		case "1":
			fileHandler.addEntry();
			menu();
			break;	
		case "2":
			translate();
			menu();
			break;
		case "3":
			fileHandler.saveToXMLFile();
			menu();
			break;
		case "4":
			System.out.println("\nExitting..");
			break;
		default:
			System.out.println("Invalid input!\n");
			menu();
			break;
		}	
	}
	

	
}
