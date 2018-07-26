package com.example.gagan.p2p.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Gagan on 6/26/2018.
 */

public class Utils {
    public static String FirstLetterCap(String input) {
        if (input == null || input.trim().length() == 0)
            return "";
        String output = input.substring(0, 1).toUpperCase() + input.substring(1);
        return output;
    }

    public static String getMoneyFormat(int amount) {
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }
}
