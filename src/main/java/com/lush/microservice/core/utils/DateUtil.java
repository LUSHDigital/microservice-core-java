package com.lush.microservice.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.stereotype.Component;

/**
 * Date Format Util
 *
 * @author Jelly
 * @author Is
 *
 */
@Component
public class DateUtil {

  /**
   * Get Now local year(only year)
   */
  public Integer getNowYear() {
    return LocalDate.now().getYear();
  }

  /**
   * Get Now local month(only month)
   */
  public Integer getNowMonth() {
    return LocalDate.now().getMonthValue();
  }

  /**
   * Get Now local date(only date)
   */
  public LocalDate getNowDate() {
    return LocalDate.now();
  }

  /**
   * Get Now local time(year, monthm, date, time)
   */
  public LocalDateTime getNowTime() {
    return LocalDateTime.now();
  }

  /**;
   * Get Now local time only(only time)
   */
  public LocalTime getOnlyNowTime() {
    return LocalTime.now();
  }

  /**
   * Get Now local time without nanosecond.
   */
  public LocalDateTime getDateTimeWithoutNanosec() {
    return LocalDateTime.now().withNano(0);
  }
}
