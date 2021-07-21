package com.licious.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Utils {
    public static Date convertString_toDate(String birth_date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(birth_date);
    }
    public static LocalDate convertDate_toLocalDate(Date birth_date)
    {
        Instant instant= birth_date.toInstant();
        ZonedDateTime zone=instant.atZone(ZoneId.systemDefault());
        return zone.toLocalDate();
    }
}
