//Add a brief description of this class here using block comments 
public class Word  {
	int count[] = new int[26];  // count of each letter in the word
	int numLetters;  // number of letters in the word
	String word;  // the word

	public Word(String s) { // construct an entry from a string
		word = s;
		numLetters = 0;
		word = word.toLowerCase();
		//'a' converts to 97 as as num
		//every index in the count array begins at 0
		for(int i=0; i<count.length; i++) {  //this for loop is easier to follow
			count[i] = 0;
		}

		for (int i = word.length()-1; i >= 0; i--) {
			int ch = word.charAt(i) - 'a';  //by subtracting 'a' we are converting to bounds within 0-26
			if (ch >= 0 && ch < 26) {
				numLetters++;
				count[ch]++;  //increment the number of letters in that index of the array
			}
		}
	}

	//Rename j as index or something else 
	public boolean containsLetter(int j){
		return count[j] != 0;
	}

	public int multiFieldCompare(Word t, int leastCommonIndex)
	{
		//It would be better to change three if statements to if and else if statements
		
		if ( (containsLetter(leastCommonIndex) ) &&  !(t.containsLetter(leastCommonIndex)) )
			return 1;
		
		if ( !(containsLetter(leastCommonIndex) ) &&  (t.containsLetter(leastCommonIndex)) )
			return -1;
		
		if ( t.numLetters != numLetters )
			return (t.numLetters - numLetters);
	
		return (word).compareTo(t.word);
	}

}

