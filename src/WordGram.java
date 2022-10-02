/**
 * A WordGram object represents an immutable
 * sequence of words.
 * For use in Compsci 201, Duke University, Fall 2022
 * Add yourself as an author when you make edits
 * @author Brandon Fain
 */

public class WordGram {
	private String[] myWords; 	// Stores WordGram words
	private String myToString;	// Stores space separated words as one string
	private int myHash;			// Stores hash value of WordGram

	
	/**
	 * Constructor should generate a WordGram with size words
	 * beginning at the start index value of source array.
	 * Each element of source array should be a single word.
	 * @param source Source array, each element should be a single word
	 * @param start Index of first word for WordGram object
	 * @param size Number of elements in WordGram object
	 */
	public WordGram(String[] source, int start, int size) {
		//myWords = new String[]{source[start]};
		myWords = new String[size];
		for (int i = 0; i < size; i++) {  
			myWords[i] = source[i + start];
			//System.out.println("i equals " + i + " length is " + myWords.length);
		}
		myToString = "";
		myHash = 0;
	}


	/**
	 * Return the word at a given index of WordGram
	 * @param index of word
	 * @return String/word at index position in WordGram
	 * @throws IndexOutOfBoundsException if index < 0 or index >= length()
	 */
	public String wordAt(int index) {
		String word = myWords[index];
		return word;
	}


	/**
	 * Returns number of words in this WordGram
	 * @return order of wordgram, number of words
	 */
	public int length() {
		//int lengthCounter = 0;
		//for (String word : this.myWords) {
		//	lengthCounter += 1;
		//}
		int length = this.myWords.length;
		return length;
	}


	/** 
	 * Returns true if o is also a wordgram with the
	 * same words, otherwise returns false 
	*/
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		WordGram other = (WordGram) o;
		if (this.myWords.length != other.myWords.length) {
			return false;
		}
		for (int i=0; i < this.myWords.length; i++) {
			if (! this.myWords[i].equals(other.myWords[i])) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Returns a hashCode for WordGram object equal to 
	 * the hashCode of the space separated words stored in 
	 * the WordGram.
	 */
	@Override
	public int hashCode() {
		if (this.myHash != 0) {
			return this.myHash;
		}
		int hash;
		String allWords = this.toString();
		hash = allWords.hashCode();
		this.myHash = hash;
		return hash;
	}


	/**
	 * Return a new WordGram of the same length as this WordGram 
	 * consisting of words 1 through length-1 of this WordGram
	 * followed by last. Does NOT mutate this WordGram.
	 * @param last added as last string of returned WordGram
	 * Should be a single word
	 * @return new WordGram
	 */
	public WordGram shiftAdd(String last) {

		// build the new String[]
		int originalLength = this.myWords.length; 				//test; if length is 5	
		String[] addedWord = new String[originalLength];		// new String array with size of 5 (can do index 0-4)
		for (int i = 0; i < originalLength-1; i++) {				// this will loop 0, 1, 2, 3
			addedWord[i] = this.myWords[i+1];					// addedWord [0] = myWords[1] etc. up to 3. 
		}
		addedWord[originalLength-1] = last;

		// use new String[] to create the new WordGram object
		WordGram added = new WordGram(addedWord, 0, addedWord.length);

		// update other saved values of the object
		added.hashCode();
		added.toString();
		return added;
	}


	/**
	 * Returns space separated words stored in the WordGram
	 * as a single String.
	 */
	@Override
	public String toString() {
		if (! this.myToString.equals("")) {
			return this.myToString;
		}
		String together = String.join(" ", this.myWords);
		this.myToString = together;
		return together;
	}
}
