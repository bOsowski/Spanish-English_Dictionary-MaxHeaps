package project;

public class DictionaryEntry implements Comparable <DictionaryEntry>{

	private String spanishWord;
	private String englishWord;
	
	public DictionaryEntry(String spanishWord,String englishWord){
		this.spanishWord = spanishWord;
		this.englishWord = englishWord;
	}
	
	public String getSpanishWord() {
		return spanishWord;
	}

	public void setSpanishWord(String spanishWord) {
		this.spanishWord = spanishWord;
	}

	public String getEnglishWord() {
		return englishWord;
	}

	public void setEnglishWord(String englishWord) {
		this.englishWord = englishWord;
	}

	@Override
	public int compareTo(DictionaryEntry other) {
		return this.spanishWord.compareTo(other.getSpanishWord());
	}

	@Override
	public String toString() {
		return "DictionaryEntry [spanishWord=" + spanishWord + ", englishWord=" + englishWord + "]";
	}
	
}
