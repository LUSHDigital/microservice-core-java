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
  public int getNowYear() {
    int nowYear = LocalDate.now().getYear();
    return nowYear;
  }

  /**
   * Get Now local month(only month)
   */
  public int getNowMonth() {
    int nowMonth = LocalDate.now().getMonthValue();
    return nowMonth;
  }

  /**
   * Get Now local date(only date)
   */
  public LocalDate getNowDate() {
    LocalDate nowDate = LocalDate.now();
    return nowDate;
  }

  /**
   * Get Now local time(year, monthm, date, time)
   */
  public LocalDateTime getNowTime() {
    LocalDateTime nowTime = LocalDateTime.now();
    return nowTime;
  }

  /**
   * Get Now local time only(only time)
   */
  public LocalTime getOnlyNowTime() {
    LocalTime nowOnlyNowTime = LocalTime.now();
    return nowOnlyNowTime;
  }

}
