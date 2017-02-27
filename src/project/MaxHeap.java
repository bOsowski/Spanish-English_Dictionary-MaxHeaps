package project;

import java.util.ArrayList;
import interfaces.MaxHeapInterface;

@SuppressWarnings("rawtypes")
public class MaxHeap<T> implements MaxHeapInterface{
	private ArrayList<T> items;

	//This function uses level traversal and I found it is the most efficient as it takes advantage of the fact that the elements are semi sorted.
	public String searchHeapEfficiently(String spanishWord){
		if(spanishWord.isEmpty()) return "You have not inputted a word.";
		else if(spanishWord.charAt(0) <= 'h'){
			for(int i = items.size()-1; i>= 0; i--){
				if(((DictionaryEntry) items.get(i)).getSpanishWord().equals(spanishWord)){
					return "English definition: "+((DictionaryEntry) items.get(i)).getEnglishWord() + "\namount of steps taken: "+(items.size() - (i+1));
				}
			}
		}
		else{
			for(int i = 0; i<items.size(); i++){
				if(((DictionaryEntry) items.get(i)).getSpanishWord().equals(spanishWord)){
					return "English definition: "+((DictionaryEntry) items.get(i)).getEnglishWord() + "\namount of steps taken: "+(i+1);
				}
			}
		}
		return "No such entry in the dictionary."+ "\namount of steps taken: "+items.size();
	}
	
	//This function searches the heap using preorder traversal, nowhere as efficient as the level traversal in this case. Done just for the sake of the assignment..
	public String searchHeap(String spanishWord){
		ArrayList<Integer> visited = new ArrayList<Integer>();
		if(spanishWord.isEmpty()) return "You have not inputted a word.";
		int k = 0;
		int i = 0;
		do{
			i++;

			if(((DictionaryEntry) items.get(k)).getSpanishWord().equals(spanishWord)) break;
			
			if(!visited.contains(k)) visited.add(k);

			else if(2*k+1 < items.size() && !visited.contains(2*k+1) && ((DictionaryEntry) items.get(2*k+1)).getSpanishWord().compareTo(spanishWord) >= 0){	//if the left child exists and it hasn't been visited, exist and are smaller than the word
				k = 2*k+1;
			}
			
			else if(2*k+2 < items.size() && !visited.contains(2*k+2) && ((DictionaryEntry) items.get(2*k+2)).getSpanishWord().compareTo(spanishWord) >= 0){	//if the right child exists and it hasn't been visited, exist and are smaller than the word
				k = 2*k+2;
			}
			
			else k = (k-1)/2;
			
			if(k == 0 && visited.contains(2*k+1) && visited.contains(2*k+2) || k==0 && ((DictionaryEntry) items.get(2*k+2)).getSpanishWord().compareTo(spanishWord) < 0 && ((DictionaryEntry) items.get(2*k+1)).getSpanishWord().compareTo(spanishWord) < 0) break;	//if it doesn't make sense to go anywhere else, break.
			
		}while(k < items.size());
		
		if(spanishWord.equals(((DictionaryEntry) items.get(k)).getSpanishWord())){
			return "English definition: "+((DictionaryEntry) items.get(k)).getEnglishWord() + "\namount of steps taken: "+i;
		}
		else return "No such entry in the dictionary."+ "\namount of steps taken: "+i;
	}
	
	//Push the element down.
	@SuppressWarnings("unchecked")
	private void siftDown() {
		int k = 0;
		int l = 2*k+1;
		while(l < items.size()){
			int max=l, r=l+1;
			if(r< items.size()){
				if(((Comparable) items.get(r)).compareTo(items.get(l)) > 0){
					max++;
				}
			}
			if(((Comparable) items.get(k)).compareTo(items.get(max)) < 0){
				T temp = items.get(k);
				items.set(k, items.get(max));
				items.set(max, temp);
				k = max;
				l = 2*k+1;
			}
			else{
				break;
			}
		}
	}

	//Push the element up.
	@SuppressWarnings("unchecked")
	private void siftUp() {
		//formula for parent = (k-1)/2
		int k = items.size()-1; //current position
		T current;
		T parent;
		
		while(k > 0){
			current = items.get(k);
			parent = items.get((k-1)/2);
			
			if(((Comparable) current).compareTo(parent) > 0){ //if current is greater than parent
				items.set((k-1)/2, current);	//set current to parent's position
				items.set(k,parent);	//set parent to current's position
				k = (k-1)/2;	//update current position
			}
			else{
				break;
			}
		}	
	}
	
	public MaxHeap(){
		items = new ArrayList<T>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void add(Comparable newEntry) {
		items.add((T) newEntry); //adding the new entry to the end.
		siftUp();
		
	}

	@Override
	public Comparable removeMax() {
		
		int k = items.size()-1;
		T maxTemporary = items.get(0);
		T minTemporary = items.get(k);
		items.set(0,minTemporary);	//replace the highest element with the lowest element.
		items.remove(k);
		
		siftDown();
		
		return (Comparable) maxTemporary;
	}
	@Override
	public Comparable getMax() {
		if(!items.isEmpty()){
			return (Comparable) items.get(0);
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if(items.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public void clear() {
		items.clear();		
	} 
}
