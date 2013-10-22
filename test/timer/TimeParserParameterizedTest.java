package timer;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith (Parameterized.class)
public class TimeParserParameterizedTest {
	
	private int time;
	private String value;
	
	public TimeParserParameterizedTest (int time, String value) {
		this.time = time;
		this.value = value;
	}
	
	@Parameters
	public static Collection<Object[]> parameters () {
		Object[][] data = new Object[][] {
				{ 600,	"10"	},
				{ 0 ,	"0" 	},
				{ 1 ,	"0:1" 	},
				{ 15 ,	"0:15" 	},
				{ 15 ,	":15" 	},
				{ 0 ,	":0" 	},
				{ 75 ,	"1:15" 	}
		};
		return Arrays.asList (data);
	}
	
	@Test
	public void executeTest () throws Exception {
		assertEquals (time, TimeParser.parse (value));
	}

//	@Test
//	public void parse () {
//		assertEquals (600, TimeParser.parse ("10"));
//		assertEquals (0, TimeParser.parse ("0"));
//		assertEquals (1, TimeParser.parse ("0:1"));
//		assertEquals (15, TimeParser.parse ("0:15"));
//		assertEquals (15, TimeParser.parse (":15"));
//		assertEquals (0, TimeParser.parse (":0"));
//		assertEquals (75, TimeParser.parse ("1:15"));
//	}
}