package com.example.simpleaccountant.util;

import java.text.DecimalFormat;

public final class MoneyUtils {
    private static final DecimalFormat FORMAT = new DecimalFormat("#,##0.00");

    private MoneyUtils() {}

    public static String format(double value) {
        return FORMAT.format(value) + " " + Constants.CURRENCY;
    }
}
