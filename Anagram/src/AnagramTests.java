import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnagramTests {

	private final ByteArrayOutputStream outPrint = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errors = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outPrint));
		System.setErr(new PrintStream(errors));
	}
	
	@After
	public void cleanUp() {
		System.setOut(null);
		System.setErr(null);
	}
	
	@Test
	public void holyokeTest() {
		Anagram.main(new String[]{"holyoke"});
		String expectedString = "reading dictionary..."
				+ "\r\nmain dictionary has 79342 entries."
				+"\r\nCandidate words:"
				+"\r\nelk"
				+"\r,  hey"
				+"\r,  hoe"
				+"\r,  hoke"
				+"\r, "
				+"\nhole"
				+"\r,  holk"
				+"\r,  holy"
				+"\r,  hook"
				+"\r, "
				+"\nhoy"
				+"\r,  key"
				+"\r,  koel"
				+"\r,  kohl"
				+"\r, "
				+"\nkolo"
				+"\r,  lek"
				+"\r,  ley"
				+"\r,  loo"
				+"\r, "
				+"\nlook"
				+"\r,  lye"
				+"\r,  oho"
				+"\r,  oke"
				+"\r, "
				+"\nokeh"
				+"\r,  ole"
				+"\r,  oleo"
				+"\r,  ooh"
				+"\r, "
				+"\nyeh"
				+"\r,  yelk"
				+"\r,  yok"
				+"\r,  yoke"
				+"\r, "
				+"\nyolk"
				+"\r,  "
				+"\r\n"
				+"\r\nAnagrams of holyoke:"
				+"\r\nhook"
				+"\r ley"
				+"\r "
				+"\r\nhook"
				+"\r lye"
				+"\r "
				+"\r\nkoel"
				+"\r hoy"
				+"\r "
				+"\r\nkolo"
				+"\r yeh"
				+"\r "
				+"\r\nkolo"
				+"\r hey"
				+"\r "
				+"\r\nhole"
				+"\r yok"
				+"\r "
				+"\r\nlook"
				+"\r yeh"
				+"\r "
				+"\r\nlook"
				+"\r hey"
				+"\r "
				+"\r\noke"
				+"\r holy"
				+"\r "
				+"\r\nhoe"
				+"\r yolk"
				+"\r "
				+"\r\nooh"
				+"\r yelk"
				+"\r "
				+"\r\noho"
				+"\r yelk"
				+"\r "
				+"\r\n----holyoke----\r\n";
		assertEquals(expectedString, outPrint.toString());
	}

}
