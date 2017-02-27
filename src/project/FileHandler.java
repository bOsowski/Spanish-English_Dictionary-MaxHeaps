package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class FileHandler {
	MaxHeap<DictionaryEntry> dictionary = new MaxHeap<DictionaryEntry>();

	public FileHandler() {	
	
		try {
			loadFromXMLFile("dictionary.xml");
		} catch (FileNotFoundException e) {
			File file = new File("SpanishWords.txt");
			readTextFile(file);
			System.out.println("The xml file was not found. A default file will be loaded.\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void readTextFile(File file){
		String line;
		String spanishWord;
		
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()){
				spanishWord = "";
				line = scanner.nextLine();
				for(int i = 0; i<line.length(); i++){
					if(Character.isWhitespace(line.charAt(i))) break;
					else spanishWord = spanishWord + line.charAt(i);
				}
				line = line.substring(spanishWord.length());
				line = line.trim();
				spanishWord = spanishWord.substring(0, spanishWord.length()-1);
				dictionary.add(new DictionaryEntry(spanishWord, line));
				
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.print("File not found!");
		}

	}
	
	public void addEntry(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nAdding an entry...");
		System.out.print("Please enter the Spanish word >> ");
		String spanishWord = scanner.nextLine();
		
		System.out.print("Please enter the English word >> ");
		String englishWord = scanner.nextLine();
		
		System.out.println("\nSpanish: "+spanishWord);
		System.out.println("English: "+englishWord);
		
		System.out.print("Do you want to add this entry? (y/n) >> ");
		String choice = scanner.nextLine();
		
		switch(choice.charAt(0)){
		case 'y': 
		case 'Y':
			dictionary.add(new DictionaryEntry(spanishWord, englishWord));
			System.out.println("The entry has been added successfully!");
			break;
		default:
			System.out.println("Word was not added.");
			break;
		}
		System.out.println("\n");
	}
	
	
	@SuppressWarnings("unchecked")
	public void loadFromXMLFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{ //Lemme g/re. ok lol, haven't thought of it xd, you are better than google xd.
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("dictionary", DictionaryEntry.class);
		ObjectInputStream inputStream = null;
		inputStream = xstream.createObjectInputStream(new FileReader(fileName));
		dictionary =  (MaxHeap<DictionaryEntry>) inputStream.readObject();
		inputStream.close();
	}
	
	public void saveToXMLFile(){
		
		XStream xstream = new XStream();
		xstream.alias("dictionary", DictionaryEntry.class);
		ObjectOutputStream outputStream = null;
		
		try {
			outputStream = xstream.createObjectOutputStream(new FileWriter(new File("dictionary.xml")));
			outputStream.writeObject(dictionary);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n-- Save successfull. --");
		System.out.println("\n");
	}
	
	
	public MaxHeap<DictionaryEntry> getDictionary() {
		return dictionary;
	}

	public void setDictionary(MaxHeap<DictionaryEntry> dictionary) {
		this.dictionary = dictionary;
	}



}
