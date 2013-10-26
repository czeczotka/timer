package com.czeczotka.timer;

import static org.testng.Assert.assertEquals;

import org.junit.Before;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TimeParserTest {

    TimeParser parser;

    @Before
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
        assertEquals (parser.parse (value), time);
    }    
    
    @DataProvider (name = "parameters")
    public Object[][] parameters () {
        return new Object[][] {
            { 600,	"10"	},
            { 0 ,	"0" 	},
            { 1 ,	"0:1" 	},
            { 15 ,	"0:15" 	},
            { 15 ,	":15" 	},
            { 0 ,	":0" 	},
            { 75 ,	"1:15" 	},
            { 6010 ,	"100:10"}
        };
    }    
}