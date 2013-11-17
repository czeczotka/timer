package com.czeczotka.timer;

import static com.czeczotka.timer.Common.*;

import java.util.Arrays;
import java.util.List;

/**
 * Simple Timer application.
 * 
 * @author Jakub Czeczotka
 * @since May 2007
 */
public class Main {
	
    private static final List<String> infoParams = 
            Arrays.asList ("-version", "-info", "-help");

    private static final String INFO = 
            APP_NAME + " version " + VERSION + ". This program is freeware.\n" +
            "Jakub Czeczotka, May 2007. \n";

    public static void main (String ... args) {
        if (args.length == 1 && infoParams.contains(args[0])) {
            System.out.println (INFO);
        } else {
            new TimerFrame ();
        }
    }
}