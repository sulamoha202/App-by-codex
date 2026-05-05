package com.arcvision.arcledger.util;

public final class ValidationUtils {
    private ValidationUtils() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isPositiveAmount(double amount) {
        return amount > 0;
    }
}
