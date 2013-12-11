package com.czeczotka.timer;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeParserTest {

    TimeParser parser;

    @BeforeTest
    public void setUp () {
        parser = new TimeParser ();
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void parse_null () {
        parser.parse (null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void parse_empty () {
        parser.parse ("");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void parse_minusValue () {
        parser.parse ("-2");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void parse_wrongInput () {
        parser.parse ("abc");
    }
    
    @Test (dataProvider = "parameters")
    public void executeTest (int time, String value) throws Exception {
        assertThat (parser.parse (value), equalTo (time));
    }
    
    @DataProvider (name = "parameters")
    public Object[][] parameters () {
        return new Object[][] {
            { 600,      "10"	},
            { 0 ,	    "0" 	},
            { 1 ,	    "0:1" 	},
            { 15 ,	    "0:15" 	},
            { 15 ,	    ":15" 	},
            { 0 ,	    ":0" 	},
            { 75 ,	    "1:15" 	},
            { 6010 ,	"100:10"}
        };
    }    
}