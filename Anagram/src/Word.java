//Add a brief description of this class here using block comments 
public class Word  {
	int count[] = new int[26];  // count of each letter in the word
	int total;  // number of letters in the word
	String aword;  // the word

	public Word(String s) { // construct an entry from a string
		//Rename ch, total
		//total could become totalNumLetters
		//I can't find a good name for ch
		int ch;
		aword = s;
		total = 0;
		s = s.toLowerCase();
		//'a' converts to 97 as as num
		for (int i = 'a'; i <= 'z'; i++) count[i-'a'] = 0;

		for (int i = s.length()-1; i >= 0; i--) {
			ch = s.charAt(i) - 'a';
			if (ch >= 0 && ch < 26) {
				total++;
				count[ch]++;
			}
		}
	}

	//Rename j as index or something else 
	public boolean containsLetter(int j){
		return count[j] != 0;
	}

	//Maybe also rename t?
	public int MultiFieldCompare(Word t, int LeastCommonIndex)
	{
		//It would be better to change three if statements to if and else if statements
		
		if ( (containsLetter(LeastCommonIndex) ) &&  !(t.containsLetter(LeastCommonIndex)) )
			return 1;
		
		if ( !(containsLetter(LeastCommonIndex) ) &&  (t.containsLetter(LeastCommonIndex)) )
			return -1;
		
		if ( t.total != total )
			return (t.total - total);
	
		return (aword).compareTo(t.aword);
	}
}

