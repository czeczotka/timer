package com.czeczotka.timer;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TimeParserTest {

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void parse_null () {
        TimeParser.parse (null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void parse_empty () {
        TimeParser.parse ("");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void parse_minusValue () {
        TimeParser.parse ("-2");
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void parse_wrongInput () {
        TimeParser.parse ("abc");
    }
    
    @Test (dataProvider = "parameters")
    public void executeTest (int time, String value) throws Exception {
        assertEquals (time, TimeParser.parse (value));
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
            { 75 ,	"1:15" 	}
        };
    }    
}