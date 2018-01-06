package org.spt.common;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.spt.utils.TimeMachine;


public final class DateHelper {

  public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

  private DateHelper() {
    throw new UnsupportedOperationException("cannot instantiate helper class.");
  }

  public static LocalDate date(String date) {
    return date(date, DEFAULT_DATE_PATTERN);
  }

  public static LocalDate date(String date, String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
    return LocalDate.parse(date, formatter);
  }

  public static Date now() {
    return Date.from(TimeMachine.dateNow().atStartOfDay(ZoneId.systemDefault()).toInstant());
  }
}
