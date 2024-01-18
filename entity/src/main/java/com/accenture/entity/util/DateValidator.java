package com.accenture.entity.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    public static void validateDateTimeString(String dateTimeString) {
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

        boolean isValid = false;

        try {
            LocalDateTime.parse(dateTimeString, localDateTimeFormatter);
            isValid = true;
        } catch (DateTimeParseException ignored) {
        }

        if (!isValid) {
            try {
                LocalDate.parse(dateTimeString, localDateFormatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(
                        "String '" + dateTimeString + "' is not a valid LocalDateTime or LocalDate");
            }
        }
    }
}
