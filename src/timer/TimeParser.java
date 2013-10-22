package timer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class parsing time (minutes and seconds) to 
 * number of seconds using regular expressions. 
 * 
 * @author Jakub Czeczotka
 */
public class TimeParser {
	
	private static final String timePattern = "([\\d]*)(:[\\d]+)?";
	
	public static int parse (String time) throws IllegalArgumentException {

		if (time == null || time.length () == 0) {
			throw new IllegalArgumentException ();
		}
		
		Pattern pattern = Pattern.compile (timePattern);
		Matcher matcher = pattern.matcher (time);
		
		if (matcher.matches ()) {
			int colonPosition = time.indexOf (':');
			int minutes = 0;
			int seconds = 0;

			if (colonPosition == -1) {
				// minutes only /minutes/
				minutes = Integer.parseInt (time);				
			} else if (colonPosition == 0) {
				// seconds after a colon only /:seconds/
				seconds = Integer.parseInt (time.substring (1));
			} else {
				// minutes and seconds /minutes:seconds/
				String min = time.substring (0, colonPosition);
				String sec = time.substring (colonPosition + 1, time.length ());
				
				minutes = Integer.parseInt (min);
				seconds = Integer.parseInt (sec);
			}
			return minutes * 60 + seconds;
		} else {
			throw new IllegalArgumentException ();
		}
	}
}