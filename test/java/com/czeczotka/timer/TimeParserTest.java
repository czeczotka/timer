package com.czeczotka.timer;

import com.czeczotka.timer.TimeParser;
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
}