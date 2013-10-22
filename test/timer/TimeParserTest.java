package timer;

import org.junit.Test;

public class TimeParserTest {

	@Test (expected = IllegalArgumentException.class)
	public void parse_null () {
		TimeParser.parse (null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void parse_empty () {
		TimeParser.parse ("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void parse_minusValue () {
		TimeParser.parse ("-2");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void parse_wrongInput () {
		TimeParser.parse ("abc");
	}
}