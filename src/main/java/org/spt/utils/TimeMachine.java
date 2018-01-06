package org.spt.utils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeMachine {
  private static Clock clock = Clock.systemDefaultZone();
  private static ZoneId zoneId = ZoneId.systemDefault();

  private TimeMachine() {
    throw new UnsupportedOperationException("Cannot instantiate this class.");
  }

  public static LocalDate dateNow() {
    return LocalDate.now(getClock());
  }

  public static LocalDateTime timeNow() {
    return LocalDateTime.now(getClock());
  }

  public static void useFixedClockAt(LocalDateTime date) {
    clock = Clock.fixed(date.atZone(zoneId).toInstant(), zoneId);
  }

  public static void useSystemDefaultZoneClock() {
    clock = Clock.systemDefaultZone();
  }

  private static Clock getClock() {
    return clock;
  }
}
