package io.frank.common.util;

import java.util.Objects;

/**
 * Package io.frank.common.util
 * Description: ${todo}
 * author 016039
 * date 2019/1/28下午9:25
 */
public class ValidationUtils {
  private ValidationUtils () {}

  public static boolean isEmpty(String target) {
    return Objects.isNull(target) || target.trim().length() == 0;
  }

  public static boolean isEmpty(Object target) {
    return Objects.isNull(target);
  }
}
