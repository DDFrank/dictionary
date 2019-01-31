package io.frank.common.util;

import java.time.LocalDateTime;

/**
 * Package io.frank.common.util
 * Description: ${todo}
 * author 016039
 * date 2019/1/28下午9:45
 */
public class DateUtils {
  private DateUtils() {}

  public static String getNowString(){
    return LocalDateTime.now().toString();
  }

  public static LocalDateTime getNowDateTime() {
    return LocalDateTime.now();
  }
}
