package constants;
/**
 * 
 */

import java.io.*;

/**
 * @author Peter van der Linden
 * @version Jan 7, 1996
 *
 */
public final class UsefulConstants {
	
	private UsefulConstants() {
		//empty
	}
	
	public static final int MAXWORDS = 100000;
	public static final int MAXWORDLEN = 30;
	public static final int EOF = -1;
	
	// shorter alias for I/O streams - probably not necessary
	public static final PrintStream o = System.out;
	public static final PrintStream e = System.err;
}
