import static constants.UsefulConstants.*;

import constants.UsefulConstants;

public class Anagram extends WordList {
	static Word[] candidate = new Word[MAXWORDS];
	static int totCandidates=0,
			   minimumLength = 3;
	
	public static void main(String[] argv) {
		if (argv.length < 1 || argv.length > 3) {
			e.println("Usage: java anagram  string-to-anagram " + "[min-len [word-file]]");
			return;
		}
		
		if (argv.length >= 2)
			minimumLength = Integer.parseInt(argv[1]);
		
		// word filename is optional 3rd argument
		readDict( argv.length==3? argv[2] : "words.txt" );
		doAnagrams(argv[0]);
	}
	
	static void doAnagrams(String anag)
	{
		Word myAnagram = new Word(anag);

		
		getCandidates(myAnagram);
		printCandidate();
		
		int rootIndexEnd = sortCandidates(myAnagram);
		
		o.println("Anagrams of " + anag + ":");
		findAnagram(myAnagram, new String[50],  0, 0, rootIndexEnd);
		
		o.println("----" + anag + "----");
	}

	static void getCandidates(Word d) {
		for (int i = totCandidates = 0; i < totWords; i++)
			if (   (    dictionary[i].numLetters >= minimumLength   )
				&& (    dictionary[i].numLetters + minimumLength <= d.numLetters
					||  dictionary[i].numLetters == d.numLetters)
				&& ( fewerOfEachLetter(d.count, dictionary[i].count) )  )
				candidate[totCandidates++]=dictionary[i];
		
	}
	
	static boolean fewerOfEachLetter(int anagCount[], int entryCount[])
	{
		for (int i = 25; i >=0; i--)
			if (entryCount[i] > anagCount[i]) return false;
		return true;
	}
	
	static void printCandidate()
	{
		o.println("Candidate words:");
		for (int i=0; i < totCandidates; i++) {
			o.print( candidate[i].word + ", " + ((i%4 ==3) ?"\n":" " ) );
		}
		o.println("");
		o.println();
	}

	static void findAnagram(
		Word d,
		String wordArray[],
		int level, int startAt, int endAt) 
	{
		int i, j;
		boolean enoughCommonLetters;
		Word wordToPass = new Word("");
		
		for (i = startAt; i < endAt; i++) {
			enoughCommonLetters = true;
			for (j = 25; j >= 0 && enoughCommonLetters; j--)
				if (d.count[j] < candidate[i].count[j])
					enoughCommonLetters = false;
			
			if (enoughCommonLetters) {
				wordArray[level] = candidate[i].word;
				wordToPass.numLetters = 0;
				for (j = 25; j >= 0; j--) {
					wordToPass.count[j] = (byte) (d.count[j] - candidate[i].count[j] );
					if ( wordToPass.count[j] != 0 ) {
						wordToPass.numLetters += wordToPass.count[j];
					}
				}
				if (wordToPass.numLetters == 0) {
					/* Found a series of words! */
					for (j = 0; j <= level; j++)
						o.print(wordArray[j] + " ");
					o.println();
				} else if (wordToPass.numLetters < minimumLength) {
					; /* Don't call again */
				} else {
					findAnagram(wordToPass, wordArray, level+1,i, totCandidates);
				}
			}
		}
	}

	static int sortCandidates(Word d)
	{
		int[] masterCount=new int[26];
		int leastCommonIndex=0, leastCommonCount;
		int i, j;
		
		for (j = 25; j >= 0; j--) masterCount[j] = 0;
		for (i = totCandidates-1; i >=0; i--)
			for (j = 25; j >=0; j--)
				masterCount[j] += candidate[i].count[j];
		
		leastCommonCount = MAXWORDS * 5;
		for (j = 25; j >= 0; j--)
			if (    masterCount[j] != 0
				 && masterCount[j] < leastCommonCount
				 && d.containsLetter(j)  ) {
				leastCommonCount = masterCount[j];
				leastCommonIndex = j;
			}
		
		quickSort(0, totCandidates-1, leastCommonIndex );
		
		for (i = 0; i < totCandidates; i++)
			if (candidate[i].containsLetter(leastCommonIndex))
				break;
		
		return i;
	}

	static void quickSort(int left, int right, int leastCommonIndex)
	{
		// standard quicksort from any algorithm book
		if (left >= right) return;
		swap(left, (left+right)/2);
		int last = left;
		for (int i=left+1; i <=right; i++)  /* partition */ {
			//if the word at index i does not have a letter that the leftmost word does
			if (candidate[i].multiFieldCompare ( candidate[left], leastCommonIndex ) ==  -1 ) {
				//increment "last" by one and swap it with the word at our current index
				swap( ++last, i);
			}
		}
		//swap "last" with our leftmost word
		swap(last, left);
		//recursively call the sort on the left half of the list, and the right
		quickSort(left, last-1, leastCommonIndex);
		quickSort(last+1,right, leastCommonIndex);
	}
	
	static void swap(int d1, int d2) {
		Word tmp = candidate[d1];
		candidate[d1] = candidate[d2];
		candidate[d2] = tmp;
	}
}